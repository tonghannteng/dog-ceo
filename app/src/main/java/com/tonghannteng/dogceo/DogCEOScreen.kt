package com.tonghannteng.dogceo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.tonghannteng.dogceo.data.state.DogState

@Composable
fun DogCEOScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: MainViewModel
) {

    PerformOnLifecycle(
        lifecycleOwner = lifecycleOwner,
        onCreate = viewModel::getRandomDog
    )

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier.height(500.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val result = viewModel.state.collectAsState().value) {
                is DogState.Loading -> {
                    CircularProgressIndicator()
                }

                is DogState.Error -> run {
                    viewModel::getRandomDog
                }

                is DogState.Success -> {
                    Image(
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp),
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(
                                LocalContext.current
                            ).data(data = result.data.message)
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                }).build()
                        ), contentDescription = "Dog"
                    )
                    Text(
                        text = result.data.status
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = result.data.message
                    )

                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = viewModel::getRandomDog,
        ) {
            Text(text = "Next dog!")
        }
    }
}

@Composable
fun PerformOnLifecycle(
    lifecycleOwner: LifecycleOwner,
    onCreate: () -> Unit = {},
    onResume: () -> Unit = {}
) {

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    onCreate()
                    Log.i("DogCEOScreen", "Lifecycle: ${event.name}")
                }
                Lifecycle.Event.ON_RESUME -> {
                    onResume()
                    Log.i("DogCEOScreen", "Lifecycle: ${event.name}")
                }
                else -> Log.i("DogCEOScreen", "Lifecycle: ${event.name}")
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        return@DisposableEffect onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
