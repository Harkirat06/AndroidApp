package dadm.hsingh.quotationshake.data.newquotation.model

import dadm.hsingh.quotationshake.domain.model.Quotation
import retrofit2.Response
import java.io.IOException

fun Response<RemoteQuotationDto>.toDomain() =
    if (isSuccessful) Result.success((body() as RemoteQuotationDto).toDomain())
    else Result.failure(IOException())
