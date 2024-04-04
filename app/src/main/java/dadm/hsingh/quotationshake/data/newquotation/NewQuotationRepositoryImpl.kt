package dadm.hsingh.quotationshake.data.newquotation

import dadm.hsingh.quotationshake.domain.model.Quotation
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val dataSource: NewQuotationDataSource
) : NewQuotationRepository {
    override suspend fun getNewQuotation(): Result<Quotation> {
        return dataSource.getQuotation()
    }
}