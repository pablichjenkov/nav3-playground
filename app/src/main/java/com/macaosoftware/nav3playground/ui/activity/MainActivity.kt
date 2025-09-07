package com.macaosoftware.nav3playground.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.macaosoftware.nav3playground.di.AppGraph
import com.macaosoftware.nav3playground.startup.IntentExt.extractNotificationData
import com.macaosoftware.nav3playground.startup.Nav3PlaygroundApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val keepSplashScreenUntilSetupCompletes = true

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().also {
            it.setKeepOnScreenCondition { keepSplashScreenUntilSetupCompletes }
        }
        super.onCreate(savedInstanceState)
        (application as Nav3PlaygroundApplication)
            .appStartupManager.onReady { onAppGraphReady(appGraph = it) }
    }

    private fun onAppGraphReady(appGraph: AppGraph) =
        lifecycleScope.launch(context = Dispatchers.Default) {
            appGraph.mainActivityCoordinator.resolveNextActivity(
                notificationData = intent.extractNotificationData()
            ).also { nextActivityClass ->
                startActivity(
                    Intent(this@MainActivity, nextActivityClass)
                )
            }
            this@MainActivity.finish()
        }

}
