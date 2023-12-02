package com.example.androidfa23.Data

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.android.material.internal.ContextUtils.getActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


private const val CURRENT_USER = "CURRENT_USER"

@Singleton
class Repository @Inject constructor(val context: Context){


    val sharedPreference: SharedPreferences = context.getSharedPreferences(CURRENT_USER, Context.MODE_PRIVATE)


    fun getId(): Int{
        return sharedPreference.getString("id", "0")?.toInt() ?: 0
    }

    fun getToken(): String{
        return sharedPreference.getString("session_token", "0")?:"null"
    }

    fun loginUser(jsonbody: JSONObject){
        val client = OkHttpClient()
        val url = "http://35.245.150.19/login/users/"
        var str: String? = ""
        val request = Request.Builder()
            .url(url)
            .post(jsonbody.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()

        Log.e("JSON", jsonbody.toString())
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("JSON", "FAILED?")

            }

            override fun onResponse(call: Call, response: Response) {
                val res = response.body?.string()
                Log.e("JSON", "" + res )
                val session = parseSession(res)
                val editor = sharedPreference.edit()
                editor.putString("id", session?.user_id.toString())
                editor.putString("session_token", session?.session_token)
                editor.apply()

            }
        })
    }


    fun fetchAllOrgs(): String?{
        val url = "http://35.245.150.19/api/orgs/"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).get().build()
        var res: String? = ""

        val response = client.newCall(request).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "onFailure: Failed")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "Success!")
                res = response.body?.string()
                Log.e("JSON", "res"+ res)
            }
        })
        return res
    }

    fun fetchPeople(id: Int):String?{
        val url = ""+id.toString()
        val client = OkHttpClient()
        val request = Request.Builder().url(url).get().build()
        var res: String? = ""
        client.newCall(request).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                    Log.e(ContentValues.TAG, "onFailure: Failed")

            }

            override fun onResponse(call: Call, response: Response) {
                res = response.body?.string()
            }

        })
        return res
    }

    fun fetchReceivedInvites(id: Int): String?{
        val url = ""+id.toString()
        val client = OkHttpClient()
        val request = Request.Builder().url(url).get().build()
        var res: String? = ""
        client.newCall(request).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                    Log.e(ContentValues.TAG, "onFailure: Failed")


            }

            override fun onResponse(call: Call, response: Response) {
                res = response.body?.string()
            }

        })
        return res
    }

    fun fetchSentInvites(id: Int): String?{
        val url = ""+id.toString()
        val client = OkHttpClient()
        val request = Request.Builder().url(url).get().build()
        var res: String? = ""
        client.newCall(request).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                    Log.e(ContentValues.TAG, "onFailure: Failed")

            }

            override fun onResponse(call: Call, response: Response) {
                res = response.body?.string()
            }

        })
        return res
    }

    fun postOrg(jsonbody: JSONObject, jsonbody2: JSONObject){
        val client = OkHttpClient()
        val url = "http://35.245.150.19/register/org/"
        var returnvalue = 0
        val request = Request.Builder()
            .url(url)
            .post(jsonbody.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()
        Log.e("JSON", jsonbody.toString())
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {


                    Log.e("JSON", "FAILED?")

            }
            override fun onResponse(call: Call, response: Response){

                val s = response.body?.string()
                Log.e("JSON", s?:"null")
                val session = parseSession(s)
                val editor = sharedPreference.edit()
                editor.putString("id", session?.user_id.toString())
                editor.putString("session_token", session?.session_token)
                editor.apply()

                val index1 = s?.indexOf("id")?.plus(5)
                val index2 = s?.indexOf("session_token")?.minus(3)
                val index3 = s?.indexOf("session_token")?.plus(17)
                val index4 = s?.indexOf("session_expiration")?.minus(4)



                if (index1!=null && index2!=null && index3!=null && index4!=null){
                    returnvalue = s?.substring(index1, index2)?.toInt() ?: -1
                    val auth = s?.substring(index3, index4)?.toString() ?: "null"
                    Log.e("JSON", returnvalue.toString())
                    updateOrg(jsonbody2, returnvalue, auth)
                }
                }


        })
    }

    fun updateOrg(body: JSONObject, id: Int, auth: String){
        val client = OkHttpClient()
        val url = "http://35.245.150.19/api/orgs/${id}/"

        val request = Request.Builder()
            .url(url)
            .put(body.toString().toRequestBody(MEDIA_TYPE_JSON))
            .addHeader("Authorization", "Bearer " + auth)
            .build()
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("JSON", "FAILED?")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("JSON", "" + response.body?.string())
            }

        })
    }

    fun postPerson(jsonbody: JSONObject, jsonbody2: JSONObject){
        val client = OkHttpClient()
        val url = "http://35.245.150.19/register/user/"
        var returnvalue = 0
        val request = Request.Builder()
            .url(url)
            .post(jsonbody.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()
        Log.e("JSON", jsonbody.toString())
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {


                    Log.e("JSON", "FAILED?")

            }
            override fun onResponse(call: Call, response: Response){

                    val s = response.body?.string()

                    val index1 = s?.indexOf("id")?.plus(5)
                    val index2 = s?.indexOf("session_token")?.minus(3)

                    val index3 = s?.indexOf("session_token")?.plus(17)
                    val index4 = s?.indexOf("session_expiration")?.minus(4)

                    if (index1!=null && index2!=null  && index3!=null && index4!=null){
                        returnvalue = s?.substring(index1, index2)?.toInt() ?: -1

                        val auth = s?.substring(index3, index4)?.toString() ?: "null"
                        Log.e("JSON", returnvalue.toString())
                        updatePerson(jsonbody2, returnvalue, auth)
                    }
            }

        })
    }

    fun updatePerson(jsonbody: JSONObject, id: Int, auth: String){
        val client = OkHttpClient()
        val url = "http://35.245.150.19/api/users/${id}/"
        var str: String? = ""
        val request = Request.Builder()
            .url(url)
            .put(jsonbody.toString().toRequestBody(MEDIA_TYPE_JSON))
            .addHeader("Authorization", "Bearer " + auth)
            .build()

        Log.e("JSON", jsonbody.toString())
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                    Log.e("JSON", "FAILED?")

            }

            override fun onResponse(call: Call, response: Response) {
                    Log.e("JSON", "" + response.body?.string())
            }
        })
    }

    fun postRequest(body: JSONObject){
        val client = OkHttpClient()
        val url = ""

        val request = Request.Builder()
            .url(url)
            .post(body.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                    Toast.makeText(context, "Failed to create request", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call, response: Response) {
                    Toast.makeText(context, "Successfully created request",
                        Toast.LENGTH_SHORT).show()

            }

        })
    }


    private fun parseSession(res : String?): SessionClass? {
        try{
            val moshi = Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()
            val jsonAdapter: JsonAdapter<SessionClass> = moshi.adapter(SessionClass::class.java)
            val parsedOrg =  jsonAdapter.fromJson(res)
            Log.e("JSON", "success")
            Log.e("JSON", parsedOrg.toString())
            return parsedOrg
        }catch (x:Exception){
            Log.e("error", x.toString())
            return null
        }
    }

    companion object {
        val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    }
}