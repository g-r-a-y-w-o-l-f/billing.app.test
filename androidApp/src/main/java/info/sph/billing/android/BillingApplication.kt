package info.sph.billing.android

import android.app.Application
import android.content.Context
import info.sph.billing.android.di.NetworkModule
import info.sph.billing.android.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Serhii Polishchuk on 23.12.24
 */
class BillingApplication: Application() {

    companion object {
        lateinit var instance: BillingApplication
        fun applicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
//        startKoin(this, biilingApp + localandroidDatasourceModule)


        initKoinDI()
    }

    private fun initKoinDI(){
        startKoin {
            logger(AndroidLogger(Level.DEBUG))
            androidContext(applicationContext)
            modules(
                NetworkModule,
                ViewModelModule
            )
        }
    }

}