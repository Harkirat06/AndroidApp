package dadm.hsingh.quotationshake.data.newquotation

import dadm.hsingh.quotationshake.domain.model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation(): Result<Quotation>
}