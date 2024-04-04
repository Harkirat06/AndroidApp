package dadm.hsingh.quotationshake.data.settings

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl @Inject constructor(private val settingsDataSource: SettingsDataSource) : SettingsRepository {
    override fun getUserName(): Flow<String> {
        return settingsDataSource.getUserName()
    }

    override suspend fun getUserNameSnapshot(): String {
        return settingsDataSource.getUserNameSnapshot()
    }

    override suspend fun setUserName(userName: String) {
        settingsDataSource.setUserName(userName)
    }
}
