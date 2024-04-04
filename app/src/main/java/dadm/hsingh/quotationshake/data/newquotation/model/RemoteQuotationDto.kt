package dadm.hsingh.quotationshake.data.newquotation.model

import com.squareup.moshi.JsonClass
import dadm.hsingh.quotationshake.domain.model.Quotation

@JsonClass(generateAdapter = true)
data class RemoteQuotationDto(
    val quoteText: String,
    val quoteAuthor: String,
    val senderName: String,
    val senderLink: String,
    val quoteLink: String
){
    fun toDomain() = Quotation(id = quoteLink, text = quoteText, author = quoteAuthor)

}
