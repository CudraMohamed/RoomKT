package cudra.mohamed.roomkt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cudra.mohamed.roomkt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val newCourseActivityRequestCode = 1
    private val courseViewModel: CourseViewModel by viewModels {
        CourseViewModelFactory((application as CourseApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = CourseRVAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        courseViewModel.allCourse.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewCourseActivity::class.java)
            startActivityForResult(intent, newCourseActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newCourseActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewCourseActivity.EXTRA_REPLY)?.let {
                val word = Course(it)
                courseViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }

    }

}