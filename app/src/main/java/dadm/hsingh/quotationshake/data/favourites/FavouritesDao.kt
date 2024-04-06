package dadm.hsingh.quotationshake.data.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dadm.hsingh.quotationshake.data.favourites.model.DatabaseQuotationDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotation(quotation: DatabaseQuotationDto)

    @Delete
    suspend fun deleteQuotation(quotation: DatabaseQuotationDto)

    @Query("SELECT * FROM ${FavouritesContract.FavouritesTable.TABLE_NAME}")
    fun getAllQuotations(): Flow<List<DatabaseQuotationDto>>

    @Query("SELECT * FROM ${FavouritesContract.FavouritesTable.TABLE_NAME} WHERE ${FavouritesContract.FavouritesTable.COLUMN_ID} = :id")
    fun getQuotationById(id: String): Flow<DatabaseQuotationDto?>

    @Query("DELETE FROM ${FavouritesContract.FavouritesTable.TABLE_NAME}")
    suspend fun deleteAllQuotations()
}
