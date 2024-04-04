package dadm.hsingh.quotationshake.data.newquotation

import dadm.hsingh.quotationshake.domain.model.Quotation
import javax.inject.Inject
import kotlin.random.Random

class NewQuotationDataSourceImpl @Inject constructor() : NewQuotationDataSource {
    override suspend fun getQuotation(): Result<Quotation> {
        return try {
            if (Random.nextInt(100) < 90) {
                val num = (0..99).random()
                Result.success(
                    Quotation(
                        id = "$num",
                        text = "Quotation text #$num",
                        author = "Author #$num"
                    ))
            } else {
                throw Exception("Error al obtener la cita")
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}