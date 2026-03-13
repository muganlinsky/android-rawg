package az.mamedali.rawg

import android.app.Application
import az.mamedali.rawg.koin.appModules
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber
import timber.log.Timber.DebugTree

class RawgApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@RawgApplication)
            modules(appModules)
        }
    }
}
