package com.macaosoftware.nav3playground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {

    private val keepSplashScreenUntilSetupCompletes = true

    // Initializers will be retrieved from Dagger lazily
    // @Inject
    // var initializers: Lazy<List<Initializers>>

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().also {
            it.setKeepOnScreenCondition { keepSplashScreenUntilSetupCompletes }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startAppSetup()
    }

    private fun startAppSetup() = lifecycle.coroutineScope.launch(Dispatchers.Default) {
        delay(duration = 3.seconds)

        // Inject Initializers from Dagger here and run their execution
        // initializers.forEach { it.initialize() }

        // Once App setup is done launch the next activity from the main thread
        withContext(Dispatchers.Main) {
            resolveNextActivity().also {
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

    private fun resolveNextActivity(): Class<out ComponentActivity> {
        /*
        return if (user.hasReservation) {
            ReservationHomeActivity::class.java
        } else {
            NoReservationHome::class.java
        }
        */
        return HomeActivity::class.java
    }
}
