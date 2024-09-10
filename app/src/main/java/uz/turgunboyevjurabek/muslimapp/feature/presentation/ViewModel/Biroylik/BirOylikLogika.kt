package uz.turgunboyevjurabek.muslimapp.feature.presentation.ViewModel.Biroylik

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.birOylk.BirOylik
import uz.turgunboyevjurabek.muslimapp.feature.domein.repozitory.MyRepozitor
import uz.turgunboyevjurabek.muslimapp.core.utils.Resource
import javax.inject.Inject

@HiltViewModel
class BirOylikLogika @Inject constructor(private val myRepozitor: MyRepozitor):ViewModel() {
    private val birOylikLiveData=MutableLiveData<Resource<BirOylik>>()
    fun getMontData():MutableLiveData<Resource<BirOylik>>{
        viewModelScope.launch {
            birOylikLiveData.postValue(Resource.loading("Loading at BirOylikLogika"))
            try {
                val getData=async {
                    myRepozitor.getMonthApi()
                }.await()
                birOylikLiveData.postValue(Resource.success(getData))
            }catch (e:Exception){
                birOylikLiveData.postValue(Resource.error(e.message))
            }
        }

        return birOylikLiveData
    }

}