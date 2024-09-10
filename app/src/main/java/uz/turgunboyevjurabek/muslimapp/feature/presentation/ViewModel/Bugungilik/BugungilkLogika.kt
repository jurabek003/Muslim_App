package uz.turgunboyevjurabek.muslimapp.feature.presentation.ViewModel.Bugungilik

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.bugungilik.Bugungi
import uz.turgunboyevjurabek.muslimapp.feature.domein.repozitory.MyRepozitor

import uz.turgunboyevjurabek.muslimapp.core.utils.Resource
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