package dadm.hsingh.quotationshake.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsDataSource {
    companion object {
        val USERNAME_KEY = stringPreferencesKey("username")
        val LANGUAGE_KEY = stringPreferencesKey("language")
    }

    override fun getUserName(): Flow<String> = getValue(USERNAME_KEY)
    override suspend fun getUserNameSnapshot(): String = getValueSnapshot(USERNAME_KEY)
    override suspend fun setUserName(userName: String) = setValue(USERNAME_KEY, userName)

    override fun getLanguage(): Flow<String> = getValue(LANGUAGE_KEY)
    override suspend fun getLanguageSnapshot(): String {
        val language = getValueSnapshot(LANGUAGE_KEY)
        if(language == ""){
            return "en"
        }
        return language
    }
    override suspend fun setLanguage(language: String) = setValue(LANGUAGE_KEY, language)

    private fun getValue(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else throw exception
        }.map { preferences ->
            preferences[key] ?: ""
        }
    }

    private suspend fun getValueSnapshot(key: Preferences.Key<String>): String {
        return dataStore.data.first()[key] ?: ""
    }

    private suspend fun setValue(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}
