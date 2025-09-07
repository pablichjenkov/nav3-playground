package com.macaosoftware.nav3playground.startup

import androidx.activity.ComponentActivity
import com.macaosoftware.nav3playground.auth.IsUserHasReservationUseCase
import com.macaosoftware.nav3playground.ui.activity.HomeActivity
import com.macaosoftware.nav3playground.ui.activity.ReservationActivity
import dev.zacsweers.metro.Inject

@Inject
class MainActivityCoordinator(
    private val isUserHasReservationUseCase: IsUserHasReservationUseCase
) {

    suspend fun resolveNextActivity(notificationData: NotificationData?): Class<out ComponentActivity> {

        /**
         * Check if the App was started from a notification then route accordingly
         * */
        return if (notificationData != null) {
            resolveNextActivityForNotificationLaunch(notificationData)
        } else {
            resolveNextActivityForRegularLaunch()
        }
    }

    private suspend fun resolveNextActivityForNotificationLaunch(
        notificationData: NotificationData
    ): Class<out ComponentActivity> {
        return if (isUserHasReservationUseCase.invoke()) {
            ReservationActivity::class.java
        } else {
            HomeActivity::class.java
        }
    }

    private suspend fun resolveNextActivityForRegularLaunch(): Class<out ComponentActivity> {

        return if (isUserHasReservationUseCase.invoke()) {
            ReservationActivity::class.java
        } else {
            HomeActivity::class.java
        }
    }
}
