package pl.wp.dogs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pl.wp.dogs.ui.breed_details.BreedDetailsScreen
import pl.wp.dogs.ui.breeds_list.BreedsListScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  NavHost(modifier = modifier, navController = navController, startDestination = BREEDS_LIST_ROUTE) {
    composable(BREEDS_LIST_ROUTE) {
      BreedsListScreen() { navController.navigate(it) }
    }
    composable(
      route = BREED_DETAILS_ROUTE,
      arguments = listOf(navArgument(BREED_NAME_ARG) { type = NavType.StringType })
      ) {
      BreedDetailsScreen()
    }
  }
}

const val BREEDS_LIST_ROUTE = "breedsList"
const val BREED_DETAILS_ROUTE = "breedDetails/{breedName}"
const val BREED_NAME_ARG = "breedName"