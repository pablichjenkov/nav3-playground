package com.macaosoftware.nav3playground.startup

import androidx.activity.ComponentActivity
import com.macaosoftware.nav3playground.ui.activity.HomeActivity

class MainActivityCoordinator {

    fun resolveNextActivity(notificationData: NotificationData?): Class<out ComponentActivity> {

        /**
         * Check if the App was started from a notification then route accordingly
         * */
        return if (notificationData != null) {
            resolveNextActivityForNotificationLaunch(notificationData)
        } else {
            resolveNextActivityForRegularLaunch()
        }
    }

    private fun resolveNextActivityForNotificationLaunch(
        notificationData: NotificationData
    ): Class<out ComponentActivity> {
        return HomeActivity::class.java
    }

    private fun resolveNextActivityForRegularLaunch(): Class<out ComponentActivity> {
        /*
        return if (user.hasReservation) {
            ReservationHomeActivity::class.java
        } else {
            HomeActivity::class.java
        }
        */
        return HomeActivity::class.java
    }
}
