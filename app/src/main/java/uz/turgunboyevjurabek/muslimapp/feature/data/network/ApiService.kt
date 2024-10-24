package uz.turgunboyevjurabek.muslimapp.feature.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import retrofit2.http.GET
import retrofit2.http.Query
import uz.turgunboyevjurabek.muslimapp.core.utils.ConsItem
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.birHaftalik.Birhaftalik
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.birOylk.BirOylik
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.bugungilik.Bugungi
import uz.turgunboyevjurabek.muslimapp.core.utils.DateUtil

interface ApiService {

    /**
     * Bugungilik Namoz vaqtlari
     */
    @GET("present/day")
    suspend fun getTodayTime(
        @Query("region") region:String
    ): Bugungi


    /**
     * Bir Haftalik Namoz vaqtlari
     */
    @GET("present/week")
    suspend fun getWeekTime(
        @Query("region") region:String
    ): Birhaftalik


    /**
     * Bir Oylik Namoz vaqtlari
     */
    @RequiresApi(Build.VERSION_CODES.O)
    @GET("monthly")
    suspend fun getMonthTime(
        @Query("region") region:String,
        @Query("month") month:Int= DateUtil.presentMonth
    ): BirOylik


}