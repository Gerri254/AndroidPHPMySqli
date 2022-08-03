package com.gerald.androidphpmysql


import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName: EditText = findViewById(R.id.inputName)
        val inputEmail: EditText = findViewById(R.id.inputEmail)
        val inputPhone: EditText = findViewById(R.id.inputPhone)
        val inputAddress: EditText = findViewById(R.id.inputAddress)
        val inputCity: EditText = findViewById(R.id.inputCity)
        val inputCountry: EditText = findViewById(R.id.inputCountry)


        val buttonSave: Button = findViewById(R.id.buttonSave)
        val buttonFetch: Button = findViewById(R.id.buttonFetch)

        //start of adding data
        val queue = Volley.newRequestQueue(this)
        val url = "https://android.emobilis.ac.ke/insert.php"

        buttonSave.setOnClickListener {
            val name = inputName.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val phone = inputPhone.text.toString().trim()
            val address = inputAddress.text.toString().trim()
            val country = inputCountry.text.toString().trim()
            val city = inputCity.text.toString().trim()
            //regex

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty() && country.isNotEmpty() && city.isNotEmpty()) {
                //save
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Saving")
                progressDialog.setMessage("Processing")
                progressDialog.show()
                val request = object : StringRequest(Method.POST, url, {
                    //TODO USE A Bottomsheetdialog
                    progressDialog.dismiss()
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                    inputName.text.clear()
                    inputEmail.text.clear()
                    inputPhone.text.clear()
                    inputAddress.text.clear()
                    inputCity.text.clear()
                    inputCountry.text.clear()
                }, {
                    progressDialog.dismiss()
                    Log.e("SAVING", "onCreate:", it)
                    Toast.makeText(this, "Error happened while saving user", Toast.LENGTH_SHORT)
                        .show()
                }) {
                    override fun getParams(): MutableMap<String, String>? {
                        val map = HashMap<String, String>()
                        map["name"] = name
                        map["email"] = email
                        map["phone"] = phone
                        map["address"] = address
                        map["country"] = country
                        map["city"] = city
                        return map

                    }
                }
                queue.add(request)

            } else {
                Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show()
            }

        }

        buttonFetch.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }


        /* *//*val url = "https://android.emobilis.ac.ke/fetch.php"//use this url to get to the data
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
        queue.add(request)*//*
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
    }*/
        //JSON-Java Script Object Notation
    }
}