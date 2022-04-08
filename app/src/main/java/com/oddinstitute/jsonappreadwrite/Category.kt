package com.oddinstitute.jsonappreadwrite

import PointFAsStringSerializer
import android.content.Context
import android.graphics.PointF
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.io.*

@Serializable
class Category ()
{
    var tasks: ArrayList<Task> = arrayListOf()
    var title: String = ""

    constructor(title: String, tasks: ArrayList<Task>) : this ()
    {
        this.title = title
        this.tasks = tasks
    }


    @Serializable (with = PointFAsStringSerializer::class)
    var location: PointF = PointF()


    @Transient
    var prevLocation: PointF = PointF ()

    @Transient
    var randomPairedUser : String = ""

}



fun Category.toMap (): Map <String, Any>
{
    val outMap : HashMap <String, Any> = hashMapOf()

    outMap["title"] = title

    if (tasks.count() > 0)
    {
        val jsonCategories = arrayListOf<Map<String, Any>>()

        for (anyTask in tasks)
            jsonCategories.add(anyTask.toMap())

        outMap["tasks"] = jsonCategories
    }


    return outMap
}





fun Category.saveMap(context: Context)
{
    val categoryString = JSONObject (this.toMap()).toString()

    val externalFileDir = context.getExternalFilesDir("")

    File (externalFileDir, "to_do_list_files").mkdir()

    val file = File(context.getExternalFilesDir("to_do_list_files"),
                    "$title.json")
    try
    {
        val outStream = FileOutputStream (file)
        val printWriter = PrintWriter (outStream)
        printWriter.print(categoryString)
        printWriter.flush()
        printWriter.close()
        outStream.close()
    }
    catch (e: FileNotFoundException)
    {
        e.printStackTrace()
    }
    catch (e: IOException)
    {
        e.printStackTrace()
    }
}