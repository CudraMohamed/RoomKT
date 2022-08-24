package cudra.mohamed.roomkt

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM course_table ORDER BY course ASC")
    fun getAlphabetizedWords(): LiveData<List<Course>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(course: Course)

    @Query("DELETE FROM course_table")
    suspend fun deleteAll()
}