package dadm.hsingh.quotationshake.data.favourites

import dadm.hsingh.quotationshake.data.favourites.model.DatabaseQuotationDto
import dadm.hsingh.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun addQuotation(quotation: Quotation)
    suspend fun deleteQuotation(quotation: Quotation)
    fun getAllQuotations(): Flow<List<Quotation>>
    fun getQuotationById(id: String): Flow<Quotation?>
    suspend fun deleteAllQuotations()
}