package coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coin_list.ui.CoinItem

@Composable
fun CoinListRoute(
    viewModel: CoinListViewModel,
    onCoinClick: (String) -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    CoinListScreen(
        state = state,
        onCoinClick = onCoinClick
    )

}

@Composable
fun CoinListScreen(
    state: CoinListUiState,
    onCoinClick: (String) -> Unit
) {

    when (state) {
        CoinListUiState.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is CoinListUiState.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
            ) {

                items(
                    items = state.coins,
                    key = { it.id }
                ) {
                    CoinItem(
                        coin = it,
                        onCoinClick = onCoinClick,
                    )
                }

            }
        }

        is CoinListUiState.Error -> throw state.throwable
    }

}