package pl.wp.dogs.data.model

import com.google.gson.annotations.SerializedName

data class BreedsResponse(
  @SerializedName("message") val message: Map<String, List<String>>
)
