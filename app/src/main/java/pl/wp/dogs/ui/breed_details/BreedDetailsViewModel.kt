package pl.wp.dogs.ui.breed_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.wp.dogs.ui.navigation.BREED_NAME_ARG
import javax.inject.Inject

@HiltViewModel
class BreedsDetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
): ViewModel() {
  val breedName: String = savedStateHandle[BREED_NAME_ARG] ?: "Error"
}

