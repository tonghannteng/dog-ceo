package com.tonghannteng.dogceo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.tonghannteng.dogceo.ui.theme.DogCEOTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author: Tonghann Teng
 * @since: 12/6/23
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogCEOTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                DogCEOScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}
