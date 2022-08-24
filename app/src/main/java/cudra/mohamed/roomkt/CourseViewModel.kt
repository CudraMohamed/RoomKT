package cudra.mohamed.roomkt

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CourseViewModel(var repository: CourseRepository):ViewModel() {
    val allCourse: LiveData<List<Course>> = repository.allCourse

    fun insert(course: Course) = viewModelScope.launch{
        repository.insert(course)
    }
}
class CourseViewModelFactory(var repository: CourseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CourseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}