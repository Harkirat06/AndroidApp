package dadm.hsingh.quotationshake.data.favourites

import dadm.hsingh.quotationshake.data.favourites.model.DatabaseQuotationDto
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {
    suspend fun addQuotation(quotation: DatabaseQuotationDto)
    suspend fun deleteQuotation(quotation: DatabaseQuotationDto)
    fun getAllQuotations(): Flow<List<DatabaseQuotationDto>>
    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>
    suspend fun deleteAllQuotations()
}