package com.example.background

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyBoundService : Service() {

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Сервис создан (onCreate)")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "Сервис запущен — клиент привязался (onBind)")
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "Клиент отвязался от сервиса (onUnbind)")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Сервис завершён (onDestroy)")
    }

    companion object {
        private const val TAG = "MyBoundService"
    }
}
