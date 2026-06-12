# Background

Android-приложение, демонстрирующее работу **Bound Service** и **DownloadManager**.

## Функциональность

### Bound Service
- **Bind Service** — привязывает Activity к сервису. В Logcat выводятся логи `onCreate` и `onBind`
- **Unbind Service** — отвязывает Activity от сервиса. В Logcat выводятся логи `onUnbind` и `onDestroy`

### DownloadManager
- **Download MP3** — ставит загрузку MP3-файла в очередь через системный `android.app.DownloadManager`. Прогресс и завершение отображаются в шторке уведомлений, файл сохраняется в `Music/`

## Структура

```
app/src/main/java/com/example/background/
├── MainActivity.kt        # Две кнопки для управления сервисом + кнопка загрузки
├── MyBoundService.kt      # Bound Service с логами жизненного цикла
└── Mp3DownloadManager.kt  # Обёртка над android.app.DownloadManager
```

## Требования

- minSdk 24
- targetSdk 37
- Разрешение `INTERNET` (в AndroidManifest)
