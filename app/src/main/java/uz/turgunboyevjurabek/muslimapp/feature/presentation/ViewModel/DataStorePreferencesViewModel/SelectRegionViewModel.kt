import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


class SelectRegionViewModel(
    private val dataStoreRegion: DataStore<Preferences>
) : ViewModel() {

    private val SELECT_REGION = stringPreferencesKey("select_region")
    private val _region: MutableStateFlow<String> = MutableStateFlow("Farg'ona")

    val region: StateFlow<String> = _region.asStateFlow()

    init {
        viewModelScope.launch {
            loadRegion()
        }
    }

    private suspend fun loadRegion() {
        dataStoreRegion.data.map { preferences ->
            preferences[SELECT_REGION] ?: ""
        }.collect { savedRegion ->
            _region.value = savedRegion
        }
    }

    fun saveRegion(newRegion: String) {
        viewModelScope.launch {
            dataStoreRegion.edit { preferences ->
                preferences[SELECT_REGION] = newRegion
            }
            _region.value = newRegion
        }
    }
}
