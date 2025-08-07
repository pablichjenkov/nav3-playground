package com.macaosoftware.nav3playground.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.coroutineScope
import com.macaosoftware.nav3playground.domain.MainActivityCoordinator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {

    // Initializers will be retrieved from Dagger lazily
    // @Inject
    // var initializers: Lazy<List<Initializers>>

    private val mainActivityCoordinator = MainActivityCoordinator()
    private val keepSplashScreenUntilSetupCompletes = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().also {
            it.setKeepOnScreenCondition { keepSplashScreenUntilSetupCompletes }
        }
        super.onCreate(savedInstanceState)
        setupApplication()
    }

    private fun setupApplication() = lifecycle.coroutineScope.launch(Dispatchers.Default) {
        delay(duration = 1.seconds)

        // Inject Initializers from Dagger here and run their execution
        // initializers.forEach { it.initialize() }

        // Once App setup is done launch the next activity from the main thread

        withContext(Dispatchers.Main) {
            mainActivityCoordinator.resolveNextActivity().also {
                startActivity(
                    Intent(
                        this@MainActivity,
                        it
                    )
                )
            }
            this@MainActivity.finish()
        }

    }

}
