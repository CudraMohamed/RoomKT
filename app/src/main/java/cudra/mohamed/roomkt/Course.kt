package cudra.mohamed.roomkt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(@PrimaryKey @ColumnInfo(name = "course") val course:String)
