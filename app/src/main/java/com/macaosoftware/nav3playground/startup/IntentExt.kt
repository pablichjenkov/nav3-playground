package com.macaosoftware.nav3playground.startup

import android.content.Intent

object IntentExt {

    fun Intent.extractNotificationData(): NotificationData {
        return NotificationData(notificationId = 0)
    }
}