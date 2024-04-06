package dadm.hsingh.quotationshake.di

import android.content.Context
import androidx.room.Room
import dadm.hsingh.quotationshake.data.favourites.FavouritesContract
import dadm.hsingh.quotationshake.data.favourites.FavouritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dadm.hsingh.quotationshake.data.favourites.FavouritesDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavouritesProviderModule {

    @Provides
    @Singleton
    fun provideFavouritesDatabase(@ApplicationContext context: Context): FavouritesDatabase {
        return Room.databaseBuilder(
            context,
            FavouritesDatabase::class.java,
            FavouritesContract.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideFavouritesDao(database: FavouritesDatabase): FavouritesDao {
        return database.favouritesDao()
    }

}
