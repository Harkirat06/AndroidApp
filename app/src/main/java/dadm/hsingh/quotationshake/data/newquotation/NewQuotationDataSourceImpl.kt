package dadm.hsingh.quotationshake.data.newquotation

import dadm.hsingh.quotationshake.data.newquotation.model.RemoteQuotationDto
import dadm.hsingh.quotationshake.domain.model.Quotation
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.random.Random

class NewQuotationDataSourceImpl @Inject constructor(private val retrofit: Retrofit) : NewQuotationDataSource {
    private val retrofitQuotationService = retrofit.create(NewQuotationRetrofit::class.java)

    override suspend fun getQuotation(): Response<RemoteQuotationDto> {
        return try {
            retrofitQuotationService.getQuotation()
        } catch (e: Exception) {
            Response.error(
                400, // Could be any other code and text, because we are not using it
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }
}

