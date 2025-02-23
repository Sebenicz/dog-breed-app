package pl.wp.dogs.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.wp.dogs.data.remote.BreedApi
import pl.wp.dogs.data.repository.BreedsRepository
import pl.wp.dogs.data.repository.DefaultBreedsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides
  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://dog.ceo/api/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  fun provideApiService(retrofit: Retrofit): BreedApi {
    return retrofit.create(BreedApi::class.java)
  }

  @Provides
  fun provideBreedsRepository(apiService: BreedApi): BreedsRepository {
    return DefaultBreedsRepository(apiService)
  }
}