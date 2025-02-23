package pl.wp.dogs.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.wp.dogs.ui.navigation.NavGraph

@Composable
fun MainScreen() {
  Scaffold(Modifier.fillMaxSize()) {
    NavGraph(Modifier.padding(it))
  }
}