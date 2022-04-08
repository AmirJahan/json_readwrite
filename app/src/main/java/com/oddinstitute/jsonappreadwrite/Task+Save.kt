package com.oddinstitute.jsonappreadwrite

import android.content.Context
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*


fun Task.save(context: Context)
{
    val taskString = Json.encodeToString(this)

    val externalFileDir = context.getExternalFilesDir("")

    File (externalFileDir, "to_do_list_files").mkdir()

    val file = File(context.getExternalFilesDir("to_do_list_files"),
                    "${name}_single_task_saved.json")
    try
    {
        val outStream = FileOutputStream (file)
        val printWriter = PrintWriter (outStream)
        printWriter.print(taskString)
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