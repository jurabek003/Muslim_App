package uz.turgunboyevjurabek.muslimapp.ViewModel.Birhaftalik

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.muslimapp.Model.madels.birHaftalik.Birhaftalik
import uz.turgunboyevjurabek.muslimapp.Model.repozitory.MyRepozitor
import uz.turgunboyevjurabek.muslimapp.Model.utils.Resource
import javax.inject.Inject

@HiltViewModel
class BirHaftalikLogika @Inject constructor(private val myRepozitor: MyRepozitor):ViewModel() {

    private val getWeekLiveData=MutableLiveData<Resource<Birhaftalik>>()

    fun getWeekData():MutableLiveData<Resource<Birhaftalik>>{
        viewModelScope.launch {
            getWeekLiveData.postValue(Resource.loading("Loading at BirHaftalikLogika"))
            try {
                val getData=async {
                    myRepozitor.getWeekApi()
                }.await()
                getWeekLiveData.postValue(Resource.success(getData))
            }catch (e:Exception){
                getWeekLiveData.postValue(Resource.error(e.message))
            }
        }

        return getWeekLiveData
    }

}