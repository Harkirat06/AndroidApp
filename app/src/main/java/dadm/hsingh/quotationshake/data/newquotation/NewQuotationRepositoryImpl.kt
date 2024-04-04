package dadm.hsingh.quotationshake.data.newquotation

import dadm.hsingh.quotationshake.data.newquotation.model.toDomain
import dadm.hsingh.quotationshake.domain.model.Quotation
import dadm.hsingh.quotationshake.utils.NoInternetException
import javax.inject.Inject

class NewQuotationRepositoryImpl @Inject constructor(
    private val dataSource: NewQuotationDataSource,
    private val connectivityChecker: ConnectivityChecker
) : NewQuotationRepository {
    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (connectivityChecker.isConnectionAvailable()) {
            dataSource.getQuotation().toDomain()
        } else {
            Result.failure(NoInternetException())
        }
    }
}
