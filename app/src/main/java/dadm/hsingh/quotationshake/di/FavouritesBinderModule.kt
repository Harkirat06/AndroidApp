package dadm.hsingh.quotationshake.di

import dadm.hsingh.quotationshake.data.favourites.FavouritesDataSource
import dadm.hsingh.quotationshake.data.favourites.FavouritesDataSourceImpl
import dadm.hsingh.quotationshake.data.favourites.FavouritesRepository
import dadm.hsingh.quotationshake.data.favourites.FavouritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {
    @Binds
    abstract fun bindFavouritesDataSource(
        dataSourceImpl: FavouritesDataSourceImpl
    ): FavouritesDataSource

    @Binds
    abstract fun bindFavouritesRepository(
        repositoryImpl: FavouritesRepositoryImpl
    ): FavouritesRepository
}