package com.macaosoftware.nav3playground.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.macaosoftware.nav3playground.startup.IntentExt.extractNotificationData
import com.macaosoftware.nav3playground.startup.MainActivityCoordinator
import com.macaosoftware.nav3playground.startup.Nav3PlaygroundApplication

class MainActivity : ComponentActivity() {

    private val keepSplashScreenUntilSetupCompletes = true
    private val mainActivityCoordinator = MainActivityCoordinator()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().also {
            it.setKeepOnScreenCondition { keepSplashScreenUntilSetupCompletes }
        }
        super.onCreate(savedInstanceState)
        waitForAllInitializersReadyAndGoToNextScreen()
    }

    private fun waitForAllInitializersReadyAndGoToNextScreen() {
        (application as Nav3PlaygroundApplication).appStartupManager.onReady {
            mainActivityCoordinator.resolveNextActivity(
                notificationData = intent.extractNotificationData()
            ).also { nextActivityClass ->
                startActivity(
                    Intent(
                        this@MainActivity,
                        nextActivityClass
                    )
                )
            }
            this@MainActivity.finish()
        }
    }

}
