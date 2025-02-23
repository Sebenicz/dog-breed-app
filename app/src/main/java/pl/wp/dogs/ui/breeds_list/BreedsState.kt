package pl.wp.dogs.ui.breeds_list

import pl.wp.dogs.data.model.Breed

sealed interface BreedsUiState {
  data class Success(val breeds: List<Breed>) : BreedsUiState
  data class Error(val exception: Throwable) : BreedsUiState
  object Loading : BreedsUiState
}