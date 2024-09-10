package uz.turgunboyevjurabek.muslimapp.feature.data.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.turgunboyevjurabek.muslimapp.core.utils.ConsItem.BASE_URL


object ApiClient {
    private fun getRetrofit(): Retrofit {


            return  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

        fun getRetrofitServis(): ApiService {
            return getRetrofit().create(ApiService::class.java)
        }

}