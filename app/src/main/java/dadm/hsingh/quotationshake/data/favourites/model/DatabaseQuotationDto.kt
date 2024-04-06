package dadm.hsingh.quotationshake.data.favourites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.hsingh.quotationshake.data.favourites.FavouritesContract

@Entity(tableName = FavouritesContract.FavouritesTable.TABLE_NAME)
data class DatabaseQuotationDto(
    @PrimaryKey @ColumnInfo(name = FavouritesContract.FavouritesTable.COLUMN_ID) val id: String,
    @ColumnInfo(name = FavouritesContract.FavouritesTable.COLUMN_TEXT) val text: String,
    @ColumnInfo(name = FavouritesContract.FavouritesTable.COLUMN_AUTHOR) val author: String
)