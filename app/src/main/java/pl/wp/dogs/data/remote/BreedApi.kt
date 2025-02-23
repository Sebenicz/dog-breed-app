package pl.wp.dogs.data.remote


import pl.wp.dogs.data.model.BreedsResponse
import retrofit2.Response
import retrofit2.http.GET

interface BreedApi {
  @GET("breeds/list/all")
  suspend fun getBreeds(): Response<BreedsResponse>
}