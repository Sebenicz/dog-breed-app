package pl.wp.dogs.data.repository

import pl.wp.dogs.data.model.Breed
import pl.wp.dogs.data.remote.BreedApi
import javax.inject.Inject

interface BreedsRepository {
  suspend fun getBreeds(): List<Breed>
}

class DefaultBreedsRepository @Inject constructor(
  private val apiService: BreedApi
): BreedsRepository {
  override suspend fun getBreeds(): List<Breed> {
    val response = apiService.getBreeds()
    return if (response.isSuccessful) {
      response.body()!!.message.map { Breed(it.key) }
    } else throw Exception("Failed to fetch breeds")
  }
}