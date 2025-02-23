package pl.wp.dogs.ui.breeds_list

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.wp.dogs.data.model.Breed

private val ITEMS_SPACING = 12.dp

@Composable
fun BreedsListScreen(viewmodel: BreedsListViewModel = hiltViewModel(), navigate: (String) -> Unit) {
  val state by viewmodel.breedsUiState.collectAsState()

  when (state) {
    is BreedsUiState.Success -> {
      val breeds = (state as BreedsUiState.Success).breeds
      BreedList(breeds) { navigate("breedDetails/${Uri.encode(it)}") }
    }

    is BreedsUiState.Error -> {
      val error = (state as BreedsUiState.Error).exception
      val context = LocalContext.current
      Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_LONG).show()
    }

    BreedsUiState.Loading -> {}
  }
}
@Composable
fun BreedList(breeds: List<Breed>, onClick: (String) -> Unit) {
  Column(
    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(ITEMS_SPACING)
  ) {
    breeds.forEach {
      BreedItem(breed = it, onClick = onClick)
    }
  }
}
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PreviewBreedList() {
  val sampleBreeds = listOf(
    Breed(name = "Golden Retriever"),
    Breed(name = "Labrador Retriever"),
    Breed(name = "German Shepherd"),
    Breed(name = "Bulldog"),
    Breed(name = "Poodle")
  )

  BreedList(breeds = sampleBreeds, onClick = {})
}


@Composable
private fun BreedItem(breed: Breed, onClick: (String) -> Unit) {
  Text(
    text = breed.name,
    style = MaterialTheme.typography.displayMedium,
    modifier = Modifier.fillMaxWidth().clickable { onClick(breed.name) }
  )
}
