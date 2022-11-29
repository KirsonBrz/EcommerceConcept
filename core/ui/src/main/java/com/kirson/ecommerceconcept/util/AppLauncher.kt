package com.kirson.ecommerceconcept.util

import com.kirson.ecommerceconcept.entity.MapLocation

interface AppLauncher {
  fun openWebsite(url: String)
  fun openDialer(phoneNumber: String)
  fun openAppInGooglePlay(packageName: String)
  fun openMapLocation(location: MapLocation)
  fun openNotificationSettings()
}
