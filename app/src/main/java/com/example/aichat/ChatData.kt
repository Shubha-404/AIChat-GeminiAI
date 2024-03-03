package com.example.aichat

import android.graphics.Bitmap
import android.hardware.biometrics.BiometricPrompt
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {
    val api_key=BuildConfig.API_KEY


    // Get response without image
    suspend fun getResponse(prompt: String): chat{
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.0-pro-vision-latest",
            apiKey = api_key,
        )
        try {
            val response= withContext(Dispatchers.IO){
                generativeModel.generateContent(prompt)
            }
            return chat(
                prompt = response.text ?: "Error..!",
                bitmap = null,
                isFromUser = false
            )
        }
        catch (e: Exception){
            return chat(
                prompt = e.message?: "Error..!",
                bitmap = null,
                isFromUser = false
            )
        }
    }

    // Get response with image
    suspend fun getResponseWithImage(prompt: String, bitmap: Bitmap,): chat{
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.0-pro-vision-latest",
            apiKey = api_key,
        )
        try {
            val inputContent = content{
                image(bitmap)
                text(prompt)
            }
            val response= withContext(Dispatchers.IO){
                generativeModel.generateContent(inputContent)
            }
            return chat(
                prompt = response.text ?: "Error..!",
                bitmap = null,
                isFromUser = false
            )
        }
        catch (e: Exception){
            return chat(
                prompt = e.message?: "Error..!",
                bitmap = null,
                isFromUser = false
            )
        }
    }



}