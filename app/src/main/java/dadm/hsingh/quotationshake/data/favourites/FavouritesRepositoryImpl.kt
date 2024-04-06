package dadm.hsingh.quotationshake.data.favourites

import dadm.hsingh.quotationshake.data.favourites.model.DatabaseQuotationDto
import dadm.hsingh.quotationshake.data.favourites.model.toDatabaseDto
import dadm.hsingh.quotationshake.data.favourites.model.toDomain
import dadm.hsingh.quotationshake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(
    private val dataSource: FavouritesDataSource
) : FavouritesRepository {
    override suspend fun addQuotation(quotation: Quotation) {
        dataSource.addQuotation(quotation.toDatabaseDto())
    }

    override suspend fun deleteQuotation(quotation: Quotation) {
        dataSource.deleteQuotation(quotation.toDatabaseDto())
    }

    override fun getAllQuotations(): Flow<List<Quotation>> {
        return dataSource.getAllQuotations().map { list -> list.map { it.toDomain() } }
    }

    override fun getQuotationById(id: String): Flow<Quotation?> {
        return dataSource.getQuotationById(id).map { it?.toDomain() }
    }

    override suspend fun deleteAllQuotations() {
        dataSource.deleteAllQuotations()
    }
}
