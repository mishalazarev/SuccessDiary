package white.ball.data.local_storage.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import white.ball.data.local_storage.room.dao.CoffeeCoinDao
import white.ball.data.local_storage.room.dao.MusicDao
import white.ball.data.local_storage.room.dao.NoteDao
import white.ball.data.local_storage.room.dao.TagDao
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO
import white.ball.data.local_storage.room.entity.MusicDTO
import white.ball.data.local_storage.room.entity.NoteDTO
import white.ball.data.local_storage.room.entity.TagDTO
import white.ball.data.local_storage.room.entity.additional.TaskByNoteDTO

@Database(entities = [
    NoteDTO::class, CoffeeCoinDTO::class, TaskByNoteDTO::class, TagDTO::class,
    MusicDTO::class], version = 1, exportSchema = false)
abstract class SuccessDiaryDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun tagDao(): TagDao

    abstract fun coffeeCoinDao(): CoffeeCoinDao

    abstract fun musicDao(): MusicDao

}