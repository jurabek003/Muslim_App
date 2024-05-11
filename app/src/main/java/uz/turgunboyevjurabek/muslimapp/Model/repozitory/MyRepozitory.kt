package uz.turgunboyevjurabek.muslimapp.Model.repozitory


import uz.turgunboyevjurabek.muslimapp.Model.network.ApiService
import javax.inject.Inject

class MyRepozitor @Inject constructor(private val apiService: ApiService) {
    suspend fun getTodayApi()=apiService.getTodayTime()
    suspend fun getWeekApi()=apiService.getWeekTime()
    suspend fun getMonthApi()=apiService.getMonthTime()


}