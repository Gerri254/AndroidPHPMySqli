package com.gerald.androidphpmysql


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //start of adding data
        val queue = Volley.newRequestQueue(this)
        /*val url = "https://android.emobilis.ac.ke/fetch.php"//use this url to get to the data
        //telling server to give the json object
        val request = JsonObjectRequest(Request.Method.GET, url ,null,{//get data from server
            responseJson ->
            Log.d("FETCHING","onCreate : $responseJson")//successful...
            val jsonArray = responseJson.getJSONArray("users")//get details from users in json
            for (i in 0 until jsonArray.length()){

                val userJsonObject = jsonArray.getJSONObject(i)
                val name = userJsonObject.get("name")
                val phone = userJsonObject.get("phone")
                Log.d("USER","onCreate: $name : $phone")
            }
        },
            {   error ->
               Log.e("FETCHING","onCreate : Error while fetching",error)//unsuccessful
            })
        queue.add(request)*/
        var weatherUrl = "https://api.weatherapi.com/v1/current.json?key=f269d6ac5ca5477896375924220208&q=Nakuru"
        val weatherRequest = JsonObjectRequest(Request.Method.GET,weatherUrl,null,
            {
               mainJsonObject ->
               val locationObject = mainJsonObject.getJSONObject("location")
               val tempObject = mainJsonObject.getJSONObject("current")
               val conditionObject = mainJsonObject.getJSONObject("current").getJSONObject("condition")
               val conditionArea = conditionObject.get("text")
               val temp = tempObject.get("temp_c")

               val city = locationObject.get("name")
               val country = locationObject.get("country")
               Log.d("WEATHER","onCreate:$city in $country is at a temperature of $temp degrees and experiencing $conditionArea")
                //PARSE JSON OBJECT
        },
            {
                error ->
                Log.e("WEATHER","onCreate:Error while fetching weather data",error)
            })

        queue.add(weatherRequest)
    }
    //JSON-Java Script Object Notation
}