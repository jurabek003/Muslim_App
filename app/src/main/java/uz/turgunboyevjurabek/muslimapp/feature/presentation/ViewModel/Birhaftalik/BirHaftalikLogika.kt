package uz.turgunboyevjurabek.muslimapp.feature.presentation.ViewModel.Birhaftalik

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.birHaftalik.Birhaftalik
import uz.turgunboyevjurabek.muslimapp.feature.domein.repozitory.MyRepozitor
import uz.turgunboyevjurabek.muslimapp.core.utils.Resource
import javax.inject.Inject

@HiltViewModel
class BirHaftalikLogika @Inject constructor(private val myRepozitor: MyRepozitor):ViewModel() {

    private val getWeekLiveData=MutableLiveData<Resource<Birhaftalik>>()

    fun getWeekData(region:String):MutableLiveData<Resource<Birhaftalik>>{
        viewModelScope.launch {
            getWeekLiveData.postValue(Resource.loading("Loading at BirHaftalikLogika"))
            try {
                val getData=async {
                    myRepozitor.getWeekApi(region)
                }.await()
                getWeekLiveData.postValue(Resource.success(getData))
            }catch (e:Exception){
                getWeekLiveData.postValue(Resource.error(e.message))
            }
        }

        return getWeekLiveData
    }

}