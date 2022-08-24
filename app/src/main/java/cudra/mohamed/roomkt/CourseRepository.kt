package cudra.mohamed.roomkt

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class CourseRepository(private val courseDao: CourseDao){
    val allCourse: LiveData<List<Course>> = courseDao.getAlphabetizedWords()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(course: Course) {
        courseDao.insert(course)
    }
}