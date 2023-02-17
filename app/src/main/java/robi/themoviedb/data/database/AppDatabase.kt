package robi.themoviedb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BookMark::class, GenreIds::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun BookMarkDao(): BookMarkDao
    abstract fun GenreIdsDao(): GenreIdsDao
}