package com.example.androidfa23.Data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.material.internal.ContextUtils.getActivity
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
        val url = ""
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

    fun postOrg(body: JSONObject){
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

    fun postPerson(body: JSONObject): Int{
        val client = OkHttpClient()
        val url = "http://35.245.150.19/register/users/"
        var returnvalue = -1
        val request = Request.Builder()
            .url(url)
            .post(body.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Failed to create profile", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response){
                getActivity(context)?.runOnUiThread{
                    val s = response.body.toString()
                    val index1 = s.indexOf("id")
                    //returnvalue = s.substring(index1+6, index1+7).toInt()

                    Toast.makeText(context, "${s}",
                        Toast.LENGTH_LONG).show()
                    returnvalue = 0
                }
            }

        })

        return returnvalue
    }

    fun updatePerson(body: JSONObject, id: Int){
        val client = OkHttpClient()
        val url = "http://35.245.150.19/api/users/${id}/"

        val request = Request.Builder()
            .url(url)
            .post(body.toString().toRequestBody(MEDIA_TYPE_JSON))
            .build()
        val response = client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: Call, e: IOException) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Failed to update person", Toast.LENGTH_SHORT).show()
                }            }

            override fun onResponse(call: Call, response: Response) {
                getActivity(context)?.runOnUiThread{
                    Toast.makeText(context, "Successfully updated person",
                        Toast.LENGTH_SHORT).show()
                }
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


    companion object {
        val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    }
}