package com.oddinstitute.jsonappreadwrite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*
import java.util.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val task_1 = Task("Buy Eggs", 12, "Amir")
        val task_2 = Task("Call Mom", 2, "Sarah")
        val task_3 = Task("Do Homework", 12, "John")
        val task_4 = Task("Go to party", 1, "Steve")
        task_2.uid = UUID.randomUUID().toString()

        val cat_1 = Category("Home Stuff", arrayListOf(task_1, task_2, task_3))
        val cat_2 = Category("Office", arrayListOf(task_3, task_4, task_1))
        val cat_3 = Category("Other stuff", arrayListOf())

        val allCategories = arrayListOf(cat_1, cat_2, cat_3)

        val allString = Json.encodeToString(allCategories)


        save(allString)

        findViewById<Button>(R.id.loadButton).setOnClickListener {

            Task.load(this)?.let {
                Log.d("TAG", "Found: ${it.name}")
            }
        }

    }
}




fun MainActivity.save(jsonString: String)
{
    val externalFileDir = this.getExternalFilesDir("")

    File (externalFileDir, "to_do_list_files").mkdir()

    val file = File(this.getExternalFilesDir("to_do_list_files"),
                    "all_categories.json")
    try
    {
        val outStream = FileOutputStream (file)
        val printWriter = PrintWriter (outStream)
        printWriter.print(jsonString)
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