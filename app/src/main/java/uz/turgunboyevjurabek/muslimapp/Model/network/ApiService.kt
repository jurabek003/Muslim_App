package uz.turgunboyevjurabek.muslimapp.Model.network

import android.os.Build
import androidx.annotation.RequiresApi
import retrofit2.http.GET
import retrofit2.http.Query
import uz.turgunboyevjurabek.muslimapp.Model.madels.birHaftalik.Birhaftalik
import uz.turgunboyevjurabek.muslimapp.Model.madels.birOylk.BirOylik
import uz.turgunboyevjurabek.muslimapp.Model.madels.bugungilik.Bugungi
import uz.turgunboyevjurabek.muslimapp.Model.utils.DateUtil

interface ApiService {
    /**
     * Bugungilik Namoz vaqtlari
     */
    @GET("present/day")
    suspend fun getTodayTime(
        @Query("region") region:String="Farg'ona"
    ):Bugungi


    /**
     * Bir Haftalik Namoz vaqtlari
     */
    @GET("present/week")
    suspend fun getWeekTime(
        @Query("region") region:String="Farg'ona",
    ):Birhaftalik


    /**
     * Bir Oylik Namoz vaqtlari
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @GET("monthly")
    suspend fun getMonthTime(
        @Query("region") region:String="Farg'ona",
        @Query("month") month:Int=DateUtil.presentMonth
    ):BirOylik


}