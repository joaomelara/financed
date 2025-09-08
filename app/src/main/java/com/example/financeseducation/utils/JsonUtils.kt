package com.example.financeseducation.utils

import android.content.Context
import com.example.financeseducation.R
import com.example.financeseducation.model.Lesson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadLessons(context: Context): List<Lesson> {
    val inputStream = context.resources.openRawResource(R.raw.lessons)
    val json = inputStream.bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<Lesson>>() {}.type
    return Gson().fromJson(json, type)
}
