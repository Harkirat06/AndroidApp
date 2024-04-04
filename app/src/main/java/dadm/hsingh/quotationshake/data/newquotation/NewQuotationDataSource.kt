package dadm.hsingh.quotationshake.data.newquotation

import dadm.hsingh.quotationshake.domain.model.Quotation

interface NewQuotationDataSource {
    suspend fun getQuotation(): Result<Quotation>
}