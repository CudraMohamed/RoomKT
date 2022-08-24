package cudra.mohamed.roomkt

import android.content.Context
import androidx.room.CoroutinesRoom
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Course::class), version = 1, exportSchema = false)
abstract class CourseDatabase : RoomDatabase(){
    abstract fun courseDao(): CourseDao

    private class CourseDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.courseDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: CourseDao) {
            // Delete all content here.
            wordDao.deleteAll()

            // Add sample words.
            var word = Course("Hello")
            wordDao.insert(word)
            word = Course("World!")
            wordDao.insert(word)

            // TODO: Add your own words!
        }
    }
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CourseDatabase? = null

        fun getDatabase(context: Context,scope:CoroutineScope): CourseDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance =Room.databaseBuilder(
                    context.applicationContext,
                    CourseDatabase::class.java,
                    "course_database")
                        .addCallback(CourseDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}