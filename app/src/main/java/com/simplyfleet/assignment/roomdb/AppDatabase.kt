package com.simplyfleet.assignment.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        private const val DB_NAME = "User.DB"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
//            instance ?: synchronized(context) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }

            //  Log.d("hhh","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj " +instance)

            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room
                        .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            DB_NAME
                        ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .addCallback(object : Callback() {})
                        .build()

                    //      Log.d("hhh","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj inner " +instance)

                }
            }
            //instance.openHelper().getWritableDatabase(); //<<<<< FORCE OPEN
            instance?.openHelper?.writableDatabase;
            return instance
        }


    }
    // This method is inside my DBHelper class.
    @Throws(IOException::class)
    fun restoreDatabaseFile(context: Context, tempDir: String, nameOfFileToRestore: String) {
        // Close the SQLiteOpenHelper so it will commit the created empty database
        // to internal storage.
        close()
        val currentDbFile = File(context.getDatabasePath(nameOfFileToRestore).path)
        val newDbFile = File("$tempDir/$nameOfFileToRestore")
        if (newDbFile.exists()) {
            copyFile(newDbFile, currentDbFile, true)
        }
    }

    // Copy fromFile to toFile, using traditional file IO
    @Throws(IOException::class)
    open fun copyFile(fromFile: File, toFile: File?, bDeleteOriginalFile: Boolean): Boolean {
        var bSuccess = true
        val inputStream = FileInputStream(fromFile)
        val outputStream = FileOutputStream(toFile)
        var fromChannel: FileChannel? = null
        var toChannel: FileChannel? = null
        try {
            fromChannel = inputStream.getChannel()
            toChannel = outputStream.getChannel()
            fromChannel.transferTo(0, fromChannel.size(), toChannel)
        } catch (e: Exception) {
            bSuccess = false
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close()
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close()
                }
            }
//            if (bDeleteOriginalFile) {
//                fromFile.delete()
//            }
        }
        return bSuccess
    }
}