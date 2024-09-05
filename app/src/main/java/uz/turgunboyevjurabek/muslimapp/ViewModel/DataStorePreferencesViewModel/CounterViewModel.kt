package uz.turgunboyevjurabek.muslimapp.ViewModel.DataStorePreferencesViewModel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CounterViewModel(private val dataStore: DataStore<Preferences>) : ViewModel() {

    private val COUNTER_KEY = intPreferencesKey("counter")
    val counter: MutableStateFlow<Int> = MutableStateFlow(0)

    init {
        // Ilova ochilganda saqlangan qiymatni yuklash
        viewModelScope.launch {
            loadCounter()
        }
    }

    // Hisoblagich qiymatini yuklash
    private suspend fun loadCounter() {
        dataStore.data.map { preferences ->
            preferences[COUNTER_KEY] ?: 0
        }.collect { savedCounter ->
            counter.value = savedCounter
        }
    }

    // Hisoblagich qiymatini oshirish va saqlash
    fun incrementCounter() {
        viewModelScope.launch {
            val newCount = counter.value + 1
            counter.value = newCount
            saveCounter(newCount)
        }
    }

    // Hisoblagich qiymatini saqlash
    private suspend fun saveCounter(count: Int) {
        dataStore.edit { preferences ->
            preferences[COUNTER_KEY] = count
        }
    }
}