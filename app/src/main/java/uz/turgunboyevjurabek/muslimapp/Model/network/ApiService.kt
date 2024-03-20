package uz.turgunboyevjurabek.muslimapp.Model.network

import retrofit2.http.GET
import retrofit2.http.Query
import uz.turgunboyevjurabek.muslimapp.Model.madels.bugungilik.Bugungi

interface ApiService {

    @GET("present/day")
    suspend fun getTodayTime(
        @Query("region") region:String="Farg'ona"
    ):Bugungi

}