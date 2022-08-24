package cudra.mohamed.roomkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CourseRVAdapter: ListAdapter<Course, CourseViewHolder>(CourseViewHolder.CourseComparator) {

    fun onCreateViewHolder(parent: ViewGroup,viewType:Int):CourseViewHolder{
        return CourseViewHolder.create(parent)
    }
    fun onBindViewHolder(holder: CourseViewHolder,position:Int){
        val current=getItem(position)
        holder.bind(current)
    }
}
class CourseViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    private val courseItemView: TextView= itemView.findViewById(R.id.textView)

    fun bind(text: String?) {
        courseItemView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): CourseViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return CourseViewHolder(view)
        }

}
class CourseComparator: DiffUtil.ItemCallback<Course>(){
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.course == newItem.course
    }
}
}