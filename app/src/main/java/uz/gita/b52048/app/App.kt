package uz.gita.b52048.app

import android.app.Application
import uz.gita.b52048.data.pref.LocalStorage

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
    }
}