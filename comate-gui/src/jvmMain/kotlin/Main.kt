import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.Navigator
import component.MessageList
import component.TextInput
import data.remote.OpenAIRepositoryImpl
import model.ConversationViewModel
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import theme.LightTheme
import java.io.File


class MainScreen(val di: DI) : Screen {
    override val key: ScreenKey = uniqueScreenKey
    private val viewmodel: ConversationViewModel by di.instance()

    @Composable
    override fun Content() {
        LightTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(Modifier.fillMaxSize()) {
                    Column(Modifier.fillMaxSize()) {
                        MessageList(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 16.dp),
                            conversationViewModel = viewmodel
                        )
                        TextInput(viewmodel)
                    }
                }
            }
        }
    }
}

fun main() = application {
    val appDir = File("${System.getProperty("user.home")}", ".comate")

    val dataStores = PreferenceDataStoreFactory.create { appDir }

    val di = DI {
        bindSingleton<ConversationViewModel> {
            ConversationViewModel(OpenAIRepositoryImpl())
        }
    }

    Window(onCloseRequest = ::exitApplication) {
        Navigator(MainScreen(di))
    }
}
