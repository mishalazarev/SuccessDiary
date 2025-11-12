package white.ball.data.local_storage.room.util.convert

import androidx.room.TypeConverter
import com.google.gson.Gson
import white.ball.data.local_storage.room.entity.additional.TimerDTO

class TimerConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromTimerDTO(timerDTO: TimerDTO): String {
        return gson.toJson(timerDTO)
    }

    @TypeConverter
    fun toTimerDTO(json: String): TimerDTO {
        return gson.fromJson(json, TimerDTO::class.java)
    }

    @TypeConverter
    fun fromListLong(value: List<Long>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toListLong(value: String): List<Long> {
        return if (value.isEmpty()) {
            emptyList()
        } else {
            value.split(",").map { it.toLong() }
        }
    }

}