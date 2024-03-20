package uz.turgunboyevjurabek.muslimapp.ViewModel.Bugungilik

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.muslimapp.Model.madels.bugungilik.Bugungi
import uz.turgunboyevjurabek.muslimapp.Model.network.ApiClient
import uz.turgunboyevjurabek.muslimapp.Model.repozitory.MyRepozitor

import uz.turgunboyevjurabek.muslimapp.Model.utils.Resource
import uz.turgunboyevjurabek.muslimapp.Model.utils.Status
import javax.inject.Inject


@HiltViewModel
class BugungilkLogika @Inject constructor(private val myRepozitory: MyRepozitor):ViewModel() {

    private val getLiveDataToday by lazy {
        MutableLiveData<Resource<Bugungi>>()
    }

    fun todayApi():MutableLiveData<Resource<Bugungi>>{
        getLiveDataToday.postValue(Resource.loading("Loading at BugungilkLogika"))
        try {
            viewModelScope.launch {
                val data=async{
                    myRepozitory.getTodayApi()
                }.await()
                getLiveDataToday.postValue(Resource.success(data))
            }
        }catch (e:Exception){
            getLiveDataToday.postValue(Resource.error(e.message))
        }
        return getLiveDataToday
    }

}