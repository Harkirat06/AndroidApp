package dadm.hsingh.quotationshake.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SettingsDataSourceImpl @Inject constructor() : SettingsDataSource {
    override fun getUserName(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserNameSnapshot(): String {
        TODO("Not yet implemented")
    }

    override suspend fun setUserName(userName: String) {
        TODO("Not yet implemented")
    }

}

