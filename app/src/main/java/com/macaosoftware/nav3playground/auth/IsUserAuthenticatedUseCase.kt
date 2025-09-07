package com.macaosoftware.nav3playground.auth

import dev.zacsweers.metro.Inject

@Inject
class IsUserHasReservationUseCase {

    suspend operator fun invoke(): Boolean {
        return false
    }
}
