package com.example.background

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log

class Mp3DownloadManager(private val context: Context) {

    fun download(url: String, fileName: String = "audio.mp3"): Long {
        val request = DownloadManager.Request(Uri.parse(url)).apply {
            setTitle("Загрузка MP3")
            setDescription(fileName)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC, fileName)
            setMimeType("audio/mpeg")
        }

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val id = manager.enqueue(request)
        Log.d(TAG, "Загрузка поставлена в очередь. ID: $id, файл: $fileName")
        return id
    }

    companion object {
        private const val TAG = "Mp3DownloadManager"
    }
}
