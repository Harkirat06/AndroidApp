package dadm.hsingh.quotationshake.data.settings

import androidx.preference.PreferenceDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SettingsPreferenceDataStore @Inject constructor(
    private val settingsRepository: SettingsRepository
) : PreferenceDataStore() {

    override fun putString(key: String, value: String?) {
        if(value != null){
            CoroutineScope(Dispatchers.IO).launch {
                when (key) {
                    "username" -> settingsRepository.setUserName(value)
                    "language" -> settingsRepository.setLanguage(value)
                }
            }
        }
    }

    override fun getString(key: String, defValue: String?): String {
        return runBlocking(Dispatchers.IO) {
            when (key) {
                "username" -> settingsRepository.getUserNameSnapshot()
                "language" -> settingsRepository.getLanguageSnapshot()
                else -> defValue ?: ""
            }
        }
    }
}
