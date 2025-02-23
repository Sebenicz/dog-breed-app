package pl.wp.dogs.ui.breeds_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.wp.dogs.data.repository.BreedsRepository
import javax.inject.Inject

@HiltViewModel
class BreedsListViewModel @Inject constructor(
  private val repository: BreedsRepository
): ViewModel() {
  private val _breedsUiState = MutableStateFlow<BreedsUiState>(BreedsUiState.Loading)
  val breedsUiState = _breedsUiState.asStateFlow()

  init {
    loadBreeds()
  }

  private fun loadBreeds() {
    viewModelScope.launch {
      _breedsUiState.value = BreedsUiState.Loading
      try {
        val breeds = repository.getBreeds()
        if (breeds.isNotEmpty()) {
          _breedsUiState.value = BreedsUiState.Success(breeds)
        } else throw Exception("No breeds found")

      } catch (e: Exception) {
        _breedsUiState.value = BreedsUiState.Error(e)
      }
    }
  }
}

