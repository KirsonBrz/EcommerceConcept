package com.kirson.baseproject.util

import com.kirson.baseproject.entity.MapLocation

interface AppLauncher {
  fun openWebsite(url: String)
  fun openDialer(phoneNumber: String)
  fun openAppInGooglePlay(packageName: String)
  fun openMapLocation(location: MapLocation)
  fun openNotificationSettings()
}
