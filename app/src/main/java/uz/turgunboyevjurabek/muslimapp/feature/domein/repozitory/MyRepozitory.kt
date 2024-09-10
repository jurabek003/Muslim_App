package uz.turgunboyevjurabek.muslimapp.feature.domein.repozitory


import android.os.Build
import androidx.annotation.RequiresApi
import uz.turgunboyevjurabek.muslimapp.feature.data.network.ApiService
import javax.inject.Inject

class MyRepozitor @Inject constructor(private val apiService: ApiService) {
    suspend fun getTodayApi()=apiService.getTodayTime()
    suspend fun getWeekApi()=apiService.getWeekTime()
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getMonthApi()=apiService.getMonthTime()


}