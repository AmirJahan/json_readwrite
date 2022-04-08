package com.oddinstitute.jsonappreadwrite


fun Task.toMap (): Map <String, Any>
{
    val outMap : HashMap <String, Any> = hashMapOf()

    outMap["name"] = name
    outMap["number"] = number
    outMap["user"] = user

    uid?.let {
        outMap["uid"] = it
    }

    return outMap
}
