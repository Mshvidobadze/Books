package com.example.books

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BooksApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}