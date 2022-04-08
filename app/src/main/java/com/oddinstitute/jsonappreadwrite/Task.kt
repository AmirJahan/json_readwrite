package com.oddinstitute.jsonappreadwrite

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*
import java.lang.StringBuilder

@Serializable
class Task ()
{
    var name: String = ""
    var number: Int = 0
    var user: String = ""

    var uid: String? = null


    constructor(name: String, number: Int, user: String) : this ()
    {
        this.name = name
        this.number = number
        this.user = user
    }


    companion object
    {

        fun load(context: Context): Task?
        {
            var listOfTaskFiles = arrayOf<File>()

            context.getExternalFilesDir("to_do_list_files")?.let {
                val myPath = it.absolutePath

                val directory = File(myPath)

                directory.listFiles()?.let { files ->
                    listOfTaskFiles = files
                }
            }

            if (listOfTaskFiles.count() == 0)
                return null

            val myFile = listOfTaskFiles[0]
            val fileInputStream = FileInputStream(myFile)
            val fileInputStreamReader = InputStreamReader(fileInputStream)
            val bufferReader = BufferedReader(fileInputStreamReader)

            val textBuilder = StringBuilder()
            var line: String? = null

            while (run {
                    line = bufferReader.readLine()
                    line
                } != null)
            {
                textBuilder.append(line)
            }

            val foundString = textBuilder.toString()

            return Json.decodeFromString(foundString)
        }
    }

}
