import androidx.navigation.compose.composable
import ru.amalkoott.message_impl.MessageScreen
import ru.amalkoott.message_api.MessageFeatureApi

class MessageFeatureImpl(
    override val name: String,
    override val route: String
    ) : MessageFeatureApi {

    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navController: androidx.navigation.NavHostController,
        modifier: androidx.compose.ui.Modifier
    ) {
        navGraphBuilder.composable(route){
            MessageScreen(name)
        }
    }
}
