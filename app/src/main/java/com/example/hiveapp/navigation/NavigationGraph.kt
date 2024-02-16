import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hiveapp.ui.theme.screens.addHiveLocation.AddHiveLocation
import com.example.hiveapp.ui.theme.screens.createEditHive.CreateEditHiveScreen
import com.example.hiveapp.ui.theme.screens.hive.HiveScreen
import com.example.hiveapp.ui.theme.screens.home.HomeScreen
import com.example.hiveapp.ui.theme.screens.weatherScreen.WeatherScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.CreateEditHive.route) {
            CreateEditHiveScreen(navController, Screen.Home.route, 0)
        }

        composable(
            route = "${Screen.CreateEditHive.route}/{id}",
            arguments = listOf(navArgument("id") {
                defaultValue = 0
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")

            if (id != null) {
                CreateEditHiveScreen(navController, Screen.Home.route, id)
            }
        }

        composable(
            route = "${Screen.Hive.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")

            if (id != null) {
                HiveScreen(navController, id)
            }
        }

        composable(
            route = "${Screen.AddHiveLocation.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")

            if (id != null) {
                AddHiveLocation(navController, id)
            }
        }

        composable(
            route = "${Screen.Weather.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")

            if (id != null) {
                WeatherScreen(navController, id)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Hive : Screen("hive")
    object CreateEditHive : Screen("create_edit_hive")
    object AddHiveLocation : Screen("add_hive_location")
    object Weather : Screen("weather")
}
