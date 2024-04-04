package dadm.hsingh.quotationshake.data.newquotation

import dadm.hsingh.quotationshake.data.newquotation.model.RemoteQuotationDto
import dadm.hsingh.quotationshake.domain.model.Quotation
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation( lenguaje : String ): Response<RemoteQuotationDto>
}