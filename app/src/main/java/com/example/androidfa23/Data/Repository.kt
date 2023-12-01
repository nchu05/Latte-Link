package com.example.androidfa23.Data

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.android.material.internal.ContextUtils.getActivity
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

@Singleton
class Repository @Inject constructor(val context: Context){
    fun fetchAllOrgs(): String?{
        val url = "http://35.245.150.19/api/orgs/"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).get().build()
        var res: String? = ""
        client.newCall(request).enqueue(object :okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                getActivity(context)?.runOnUiThread{
                    Log.e(TAG, "onFailure: Failed")

                }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, "Success!")
                res = response.body?.string()
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
                getActivity(context)?.runOnUiThread{
                    Log.e(ContentValues.TAG, "onFailure: Failed")

                }
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
                getActivity(context)?.runOnUiThread{
                    Log.e(ContentValues.TAG, "onFailure: Failed")

                }
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
                getActivity(context)?.runOnUiThread{
                    Log.e(ContentValues.TAG, "onFailure: Failed")

                }
            }

            override fun onResponse(call: Call, response: Response) {
                res = response.body?.string()
            }

        })
        return res
    }

    fun postOrg(jsonbody: JSONObject){
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
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Failed to create organization", Toast.LENGTH_SHORT).show()
                    Log.e("JSON", "FAILED?")
                }
            }
            override fun onResponse(call: Call, response: Response){
                getActivity(context)?.runOnUiThread{
                    val s = response.body?.string()
                    val index1 = s?.indexOf("id")
                    //returnvalue = s.substring(index1+6, index1+7).toInt()
                    Toast.makeText(context, "created organization successfully", Toast.LENGTH_LONG).show()
                    //Log.e("JSON", "DID NOT FAIL")
                    Log.e("JSON", "" + response )
                    //response.body?.let { Log.e("JSON", "Post organization response after" + it.string()) }
                    returnvalue = 0
                }
            }

        })
    }

    fun updateOrg(id: Int, body: JSONObject){
        val client = OkHttpClient()
        val url = ""

        val request = Request.Builder()
            .url(url)
            .post(body.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Failed to create organization", Toast.LENGTH_SHORT).show()
                }            }

            override fun onResponse(call: Call, response: Response) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Successfully created organization",
                        Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    fun postPerson(jsonbody: JSONObject): Int{
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
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Failed to create profile", Toast.LENGTH_SHORT).show()
                    Log.e("JSON", "FAILED?")
                }
            }
            override fun onResponse(call: Call, response: Response){
                getActivity(context)?.runOnUiThread{
                    val s = response.body?.string()
                    val index1 = s?.indexOf("id")
                    //returnvalue = s.substring(index1+6, index1+7).toInt()
                    Toast.makeText(context, "created profile successfully", Toast.LENGTH_LONG).show()
                    //Log.e("JSON", "DID NOT FAIL")
                    Log.e("JSON", "" + response )
                    //response.body?.let { Log.e("JSON", "Post person response after" + it.string()) }
                    returnvalue = 0
                }
            }

        })

        return returnvalue
    }

    fun updatePerson(jsonbody: JSONObject, id: Int): String?{
        val client = OkHttpClient()
        val url = "http://35.245.150.19/api/users/${id}/"
        var str: String? = ""
        val request = Request.Builder()
            .url(url)
            .put(jsonbody.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()

        Log.e("JSON", jsonbody.toString())
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Failed to update person", Toast.LENGTH_SHORT).show()
                    Log.e("JSON", "FAILED?")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Updated profile successfully", Toast.LENGTH_LONG).show()
                    Log.e("JSON", "" + response)
                    response.body?.let { Log.e("JSON", "Update person response after" + it.string()) }
                }
            }
        })
        return str
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
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Failed to create request", Toast.LENGTH_SHORT).show()
                }            }

            override fun onResponse(call: Call, response: Response) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Successfully created request",
                        Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    companion object {
        val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    }
}