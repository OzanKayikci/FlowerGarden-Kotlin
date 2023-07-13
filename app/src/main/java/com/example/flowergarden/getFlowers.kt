package com.example.flowergarden

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader



class getFlowers {
    var context: Context? = null
    var resourceId: Int? = null

    constructor(context: Context, resourceId: Int) {
        this.context = context
        this.resourceId = resourceId
    }


    fun readJsonFromRaw(): String {
        val inputStream: InputStream = context!!.resources.openRawResource(resourceId!!)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String? = bufferedReader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = bufferedReader.readLine()
        }
        bufferedReader.close()
        return stringBuilder.toString()
    }

    fun convertToObj(): ArrayList<FlowersFromJson> {
        val jsonString = readJsonFromRaw()
        //convert json to flower object
        val gson = Gson()
        val flowerListType = object : TypeToken<ArrayList<FlowersFromJson>>() {}.type
        return gson.fromJson<ArrayList<FlowersFromJson>>(jsonString, flowerListType)
    }

    class FlowersFromJson {
        var name: String? = null
        var description: String? = null
        var image: String? = null

        constructor(name: String, description: String, image: String) {
            this.name = name
            this.description = description
            this.image = image
        }
    }
}