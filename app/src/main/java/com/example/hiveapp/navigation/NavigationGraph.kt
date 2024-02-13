import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hiveapp.ui.theme.screens.Hive.Hive
import com.example.hiveapp.ui.theme.screens.Home.Home


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route ) {
        composable(Screen.Home.route) {
            Home(navController)
        }

        composable(Screen.Hive.route) {
            Hive(navController)
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Hive : Screen("hive")
}
