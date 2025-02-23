package pl.wp.dogs.ui.breed_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BreedDetailsScreen(viewModel: BreedsDetailsViewModel = hiltViewModel()) {
  val name = viewModel.breedName
  BreedDetails(name)
}

@Composable
private fun BreedDetails(name: String) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(name)
  }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun PreviewBreedDetails() {
  BreedDetails("Pug")
}