package com.saidov.cookbook.repository.dbrepository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saidov.cookbook.modules.main.ui.model.DrinkModel



/**
 * Created by MUHAMMADJON SAIDOV on 01,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
@Database(
    entities = [DrinkModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDrinkDao(): DrinkDao


    companion object {
        //Volatile чтобы другие потоки могли сразу видеть, когда поток изменяет этот  instance
        @Volatile
        private var instance: AppDatabase? = null

        //Lock переменная для синхронизации экземпляра, чтобы убедиться,
        // что одновременно существует только один экземпляр ArticleDatabase
        private val LOCK = Any()

        //операторная функция, вызываемая всякий раз, когда мы создаем экземпляр нашей базы данных
        //если экземпляр нулевой, мы синхронизируем его с помощью LOCK:
        //LOCK: чтобы все, что происходит внутри этой функции, не могло быть доступно
        // другим потокам одновременно

        // синхронизировать экземпляр, если только null
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // проверка на ноль, чтобы убедиться, что нет другого потока,
            // который устанавливает экземпляр во что-то, в то время как мы уже установили его
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "drink.db"
            ).build()
    }
}