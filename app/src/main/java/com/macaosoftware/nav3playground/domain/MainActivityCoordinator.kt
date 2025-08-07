package com.macaosoftware.nav3playground.domain

import androidx.activity.ComponentActivity
import com.macaosoftware.nav3playground.ui.activity.HomeActivity

class MainActivityCoordinator {

    fun resolveNextActivity(): Class<out ComponentActivity> {
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