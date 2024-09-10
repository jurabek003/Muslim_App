package uz.turgunboyevjurabek.muslimapp.Model.di

import android.content.Context
import android.hardware.SensorManager
import com.google.android.gms.location.LocationServices
import uz.turgunboyevjurabek.qiblafinderexample.service.MyLocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.turgunboyevjurabek.qiblafinderexample.service.CompassSensorManager
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }


    @Singleton
    @Provides
    fun provideMyLocationManager(@ApplicationContext context: Context, fusedLocationProviderClient: FusedLocationProviderClient): MyLocationManager {
        return MyLocationManager(context, fusedLocationProviderClient)
    }

    @Provides
    @Singleton
    fun provideSensorManager(@ApplicationContext context: Context): SensorManager {
        return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

//    @Singleton
//    @Provides
//    fun provideCompassSensorManager(sensorManager: SensorManager): CompassSensorManager {
//        return CompassSensorManager(sensorManager)
//    }


}