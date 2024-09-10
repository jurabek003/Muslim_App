package uz.turgunboyevjurabek.muslimapp.ViewModel.Qibla

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.turgunboyevjurabek.muslimapp.View.UIutils.QiblaState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _qiblaState = MutableStateFlow(QiblaState())
    val qiblaState = _qiblaState.asStateFlow()


    fun updateQiblaDirection(newDirection: Float) {
        _qiblaState.update {
            it.copy(
                qiblaDirection = newDirection
            )
        }
    }

    fun updateCurrentDirection(newDirection: Float) {
        _qiblaState.update {
            it.copy(
                currentDirection = newDirection
            )
        }
    }

}