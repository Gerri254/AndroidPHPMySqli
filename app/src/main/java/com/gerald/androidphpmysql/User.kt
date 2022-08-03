package com.gerald.androidphpmysql

import android.provider.ContactsContract

data class User(
     val id : String,
     val name : String,
     val phone:  String,
     val email : String,
     val address : String,
     val city : String,
     val country : String
 )