package com.example.background

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val downloader = Mp3DownloadManager(this)

        findViewById<Button>(R.id.btnBind).setOnClickListener {
            if (!isBound) bindService(Intent(this, MyBoundService::class.java), connection, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.btnUnbind).setOnClickListener {
            if (isBound) {
                unbindService(connection)
                isBound = false
            }
        }

        findViewById<Button>(R.id.btnDownload).setOnClickListener {
            // Замените URL на любую прямую ссылку на mp3-файл
            val mp3Url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
            downloader.download(url = mp3Url, fileName = "sample.mp3")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) unbindService(connection)
    }
}
