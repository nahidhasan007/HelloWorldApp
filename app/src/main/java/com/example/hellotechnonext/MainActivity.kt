package com.example.hellotechnonext

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.hellotechnonext.coordinator.BaseChildNavGraph
import com.example.hellotechnonext.coordinator.BaseNavCoordinator
import com.example.hellotechnonext.ui.theme.HellotechnonextTheme
import com.example.hellotechnonext.view.screen.postxcomments.HomeScreen
import com.example.hellotechnonext.view.screen.postxcommentsdetails.PostCommentDetailScreen

class MainActivity : AppCompatActivity() {

    private var _appCoordinator: PlaceholderNavCoordinator? = null
    private val appCoordinator get() = _appCoordinator!!
    private var bottomPadding = 0.dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainView()
        }
    }

    @Composable
    fun MainView() {
        HellotechnonextTheme(darkTheme = false) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                bottomPadding = innerPadding.calculateBottomPadding()
                val mainModifier = Modifier.padding(innerPadding)

                val mNavHostController = androidx.navigation.compose.rememberNavController()
                _appCoordinator = PlaceholderNavCoordinator(
                    mNavHostController,
                    mainModifier,
                    createListOfScreensInsideComposable(mNavHostController),
                    HomeScreen.Route.HomeScreen,
                    this
                )
                appCoordinator.PlaceHolderNavHost()
            }
        }
    }

    private fun createListOfScreensInsideComposable(
        mNavHostController: NavHostController,
    ): List<BaseChildNavGraph> {

        val mList = mutableListOf(
            HomeScreen(mNavHostController),
            PostCommentDetailScreen(mNavHostController)
        )
        return mList
    }


}


class PlaceholderNavCoordinator(
    mNavHostController: NavHostController,
    mainModifier: Modifier,
    listOfChildNavGraphs: List<BaseChildNavGraph>,
    mStartDestination: String,
    mActivity: AppCompatActivity
) : BaseNavCoordinator(
    mNavHostController, mainModifier, listOfChildNavGraphs, mStartDestination, mActivity
) {

    @Composable
    fun PlaceHolderNavHost() {
        BaseNavHost()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HellotechnonextTheme {
        Greeting("Android")
    }
}