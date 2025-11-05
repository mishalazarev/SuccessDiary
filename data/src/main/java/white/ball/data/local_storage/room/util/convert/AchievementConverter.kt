package white.ball.data.local_storage.room.util.convert

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import white.ball.data.local_storage.room.entity.AchievementDTO

class AchievementConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromAchievementDTO(achievementDTO: AchievementDTO): String {
        return gson.toJson(achievementDTO)
    }

    @TypeConverter
    fun toAchievementDTO(json: String): AchievementDTO {
        return gson.fromJson(json, AchievementDTO::class.java)
    }

    @TypeConverter
    fun fromAchievementDTOList(achievementList: List<AchievementDTO>): String {
        return gson.toJson(achievementList)
    }

    @TypeConverter
    fun toAchievementDTOList(json: String): List<AchievementDTO> {
        if (json.isEmpty()) return listOf()
        val type = object: TypeToken<List<AchievementDTO>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromLinkedHasMapDoneList(linkedHashMap: LinkedHashMap<Int, Boolean>): String {
        return gson.toJson(linkedHashMap)
    }

    @TypeConverter
    fun toLinkedHashMapDoneList(json: String): LinkedHashMap<Int, Boolean> {
        if (json.isEmpty()) return linkedMapOf()
        val type = object : TypeToken<LinkedHashMap<Int, Boolean>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromLinkedHashMapMaxList(linkedHashMap: LinkedHashMap<Int, Int>): String {
        return gson.toJson(linkedHashMap)
    }

    @TypeConverter
    fun toLinkedHashMapMaxList(json: String): LinkedHashMap<Int, Int> {
        if (json.isEmpty()) return linkedMapOf()
        val type = object : TypeToken<LinkedHashMap<Int, Int>>() {}.type
        return gson.fromJson(json, type)
    }
}