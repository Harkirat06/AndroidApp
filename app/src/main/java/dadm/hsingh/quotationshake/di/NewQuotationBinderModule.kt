package dadm.hsingh.quotationshake.di

import dadm.hsingh.quotationshake.data.newquotation.NewQuotationDataSource
import dadm.hsingh.quotationshake.data.newquotation.NewQuotationDataSourceImpl
import dadm.hsingh.quotationshake.data.newquotation.NewQuotationRepository
import dadm.hsingh.quotationshake.data.newquotation.NewQuotationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {
    @Binds
    abstract fun bindNewQuotationRepository(
        implementation: NewQuotationRepositoryImpl
    ): NewQuotationRepository

    @Binds
    abstract fun bindNewQuotationDataSource(
        implementation: NewQuotationDataSourceImpl
    ): NewQuotationDataSource
}
