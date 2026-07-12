package white.ball.success_diary.presentation.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import white.ball.data.local_storage.room.dao.CoffeeCoinDao
import white.ball.data.local_storage.room.dao.MusicDao
import white.ball.data.local_storage.room.dao.NoteDao
import white.ball.data.local_storage.room.dao.TagDao
import white.ball.data.local_storage.room.database.SuccessDiaryDatabase
import white.ball.data.local_storage.room.implementation.CoffeeCoinRepositoryImpl
import white.ball.data.local_storage.room.implementation.MusicRepositoryImpl
import white.ball.data.local_storage.room.implementation.NoteRepositoryImpl
import white.ball.data.local_storage.room.implementation.TagRepositoryImpl
import white.ball.domain.repository.CoffeeCoinRepository
import white.ball.domain.repository.MusicRepository
import white.ball.domain.repository.NoteRepository
import white.ball.domain.repository.TagRepository

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("""
            CREATE TABLE IF NOT EXISTS Task (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                description TEXT
            )
        """.trimIndent())
        }
    }

    @Provides
    @Singleton
    fun provideSuccessDiaryDatabase(@ApplicationContext context: Context): SuccessDiaryDatabase {
        return Room.databaseBuilder(
            context,
            SuccessDiaryDatabase::class.java,
            NAME_DATABASE
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: SuccessDiaryDatabase): NoteDao {
        return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideTagDao(database: SuccessDiaryDatabase): TagDao {
        return database.tagDao()
    }

    @Provides
    @Singleton
    fun provideCoffeeCoinDao(database: SuccessDiaryDatabase): CoffeeCoinDao {
        return database.coffeeCoinDao()
    }

    @Provides
    @Singleton
    fun providesMusicDao(database: SuccessDiaryDatabase): MusicDao {
        return database.musicDao()
    }

    @Provides
    @Singleton
    fun providesNoteRepositoryImpl(noteDao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(noteDao)
    }

    @Provides
    @Singleton
    fun providesTagRepositoryImpl(tagDao: TagDao): TagRepository {
        return TagRepositoryImpl(tagDao)
    }

    @Provides
    @Singleton
    fun providesCoffeeCoinImpl(coffeeCoinDao: CoffeeCoinDao): CoffeeCoinRepository {
        return CoffeeCoinRepositoryImpl(coffeeCoinDao)
    }

    @Provides
    @Singleton
    fun providesMusicImpl(musicDao: MusicDao): MusicRepository {
        return MusicRepositoryImpl(musicDao)
    }


    const val NAME_DATABASE = "room"
}