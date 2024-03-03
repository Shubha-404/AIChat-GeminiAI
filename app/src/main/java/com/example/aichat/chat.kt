package com.example.aichat

import android.graphics.Bitmap

data class chat(
    val prompt: String,
    val bitmap: Bitmap?,
    val isFromUser: Boolean,
)
