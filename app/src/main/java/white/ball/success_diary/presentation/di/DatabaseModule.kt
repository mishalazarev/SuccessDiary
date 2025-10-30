package white.ball.success_diary.presentation.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import white.ball.data.local_storage.room.dao.NoteDao
import white.ball.data.local_storage.room.dao.TagDao
import white.ball.data.local_storage.room.dao.TaskDao
import white.ball.data.local_storage.room.dao.UserDao
import white.ball.data.local_storage.room.database.SuccessDiaryDatabase
import white.ball.data.local_storage.room.implementation.NoteImpl
import white.ball.data.local_storage.room.implementation.TagImpl
import white.ball.data.local_storage.room.implementation.TaskImpl
import white.ball.data.local_storage.room.implementation.UserImpl
import white.ball.domain.repository.NoteRepository
import white.ball.domain.repository.TagRepository
import white.ball.domain.repository.TaskRepository
import white.ball.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSuccessDiaryDatabase(@ApplicationContext context: Context): SuccessDiaryDatabase {
        return Room.databaseBuilder(
            context,
            SuccessDiaryDatabase::class.java,
            NAME_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: SuccessDiaryDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: SuccessDiaryDatabase): TaskDao {
        return database.taskDao()
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
    fun provideUserRepositoryImpl(userDao: UserDao): UserRepository {
        return UserImpl(userDao)
    }

    @Provides
    @Singleton
    fun providesTaskRepositoryImpl(taskDao: TaskDao): TaskRepository {
        return TaskImpl(taskDao)
    }

    @Provides
    @Singleton
    fun providesNoteRepositoryImpl(noteDao: NoteDao): NoteRepository {
        return NoteImpl(noteDao)
    }

    @Provides
    @Singleton
    fun providesTagRepositoryImpl(tagDao: TagDao): TagRepository {
        return TagImpl(tagDao)
    }
    private const val NAME_DATABASE = "room"

}