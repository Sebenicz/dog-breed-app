package pl.wp.dogs.ui.breed_details

import androidx.lifecycle.SavedStateHandle
import junit.framework.TestCase.assertEquals
import org.junit.Test
import pl.wp.dogs.ui.navigation.BREED_NAME_ARG

class BreedsDetailsViewModelTest {

  @Test
  fun `given breed name, when ViewModel is created, then breedName is set correctly`() {

    val savedStateHandle = SavedStateHandle(mapOf(BREED_NAME_ARG to "Labrador"))

    val viewModel = BreedsDetailsViewModel(savedStateHandle)

    assertEquals(viewModel.breedName, "Labrador")
  }

  @Test
  fun `given no breed name, when ViewModel is created, then breedName defaults to Error`() {

    val savedStateHandle = SavedStateHandle()

    val viewModel = BreedsDetailsViewModel(savedStateHandle)

    assertEquals(viewModel.breedName, "Error")
  }
}