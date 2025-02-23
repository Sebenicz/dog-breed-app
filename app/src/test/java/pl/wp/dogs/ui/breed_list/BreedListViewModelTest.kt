package pl.wp.dogs.ui.breed_list

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import pl.wp.dogs.data.model.Breed
import pl.wp.dogs.data.repository.BreedsRepository
import pl.wp.dogs.ui.breeds_list.BreedsListViewModel
import pl.wp.dogs.ui.breeds_list.BreedsUiState

@OptIn(ExperimentalCoroutinesApi::class)
class BreedsListViewModelTest {
  private lateinit var repository: BreedsRepository
  private lateinit var viewModel: BreedsListViewModel

  var dispatcher = StandardTestDispatcher()

  @Before
  fun setup() {
    repository = mock(BreedsRepository::class.java)
  }

  @Test
  fun `given breeds from repository, when ViewModel loads, then state is Success`() = runTest(dispatcher) {
    val mockBreeds = listOf(Breed("Labrador"), Breed("Golden Retriever"))
    whenever(repository.getBreeds()).thenReturn(mockBreeds)

    viewModel = BreedsListViewModel(repository)
    val state = viewModel.breedsUiState.first()
    advanceUntilIdle()
    val successState = viewModel.breedsUiState.first()
    assertEquals(BreedsUiState.Success(mockBreeds), successState)

  }

  @Test
  fun `given empty list from repository, when ViewModel loads, then state is Error`() = runTest(dispatcher) {
    whenever(repository.getBreeds()).thenReturn(listOf<Breed>())

    viewModel = BreedsListViewModel(repository)
    val state = viewModel.breedsUiState.first()
    advanceUntilIdle()
    delay(4000)
    val errorState = viewModel.breedsUiState.first()
    assertEquals(BreedsUiState.Error::class, errorState::class)
  }
}