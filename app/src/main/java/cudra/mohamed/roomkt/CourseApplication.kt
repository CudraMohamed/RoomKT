package cudra.mohamed.roomkt

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
class CourseApplication:Application() {

    val applicationScope= CoroutineScope(SupervisorJob())
    val database by lazy { CourseDatabase.getDatabase(this) }
    val repository by lazy { CourseRepository(database.courseDao()) }
}