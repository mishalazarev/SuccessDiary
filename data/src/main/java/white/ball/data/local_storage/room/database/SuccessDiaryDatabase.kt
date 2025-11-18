package white.ball.data.local_storage.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import white.ball.data.local_storage.room.dao.AchievementDao
import white.ball.data.local_storage.room.dao.CoffeeCoinDao
import white.ball.data.local_storage.room.dao.NoteDao
import white.ball.data.local_storage.room.dao.TagDao
import white.ball.data.local_storage.room.entity.AchievementDTO
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.additional.AchievementTaskDTO
import white.ball.data.local_storage.room.entity.additional.TaskDTO
import white.ball.data.local_storage.room.entity.additional.TimerDTO
import white.ball.data.local_storage.room.util.convert.AchievementConverter
import white.ball.data.local_storage.room.util.convert.TimerConverter

@Database(entities = [
    NoteDTO::class, TimerDTO::class, CoffeeCoinDTO::class, TaskDTO::class,
    TagDTO::class, AchievementDTO::class, AchievementTaskDTO::class], version = 1, exportSchema = false)
@TypeConverters(
    TimerConverter::class, AchievementConverter::class,
)
abstract class SuccessDiaryDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun tagDao(): TagDao

    abstract fun achievementDao(): AchievementDao

    abstract fun coffeeCoinDao(): CoffeeCoinDao

}