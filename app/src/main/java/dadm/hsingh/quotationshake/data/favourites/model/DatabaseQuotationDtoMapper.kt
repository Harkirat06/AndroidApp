package dadm.hsingh.quotationshake.data.favourites.model

import dadm.hsingh.quotationshake.domain.model.Quotation

fun DatabaseQuotationDto.toDomain(): Quotation {
    return Quotation(
        id = this.id,
        text = this.text,
        author = this.author
    )
}
fun Quotation.toDatabaseDto(): DatabaseQuotationDto {
    return DatabaseQuotationDto(
        id = this.id,
        text = this.text,
        author = this.author
    )
}