package com.ifts4.tpfinal.data

import Constants
import com.ifts4.tpfinal.models.Producto
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.ifts4.tpfinal.TpFinal


@Database(entities = [Producto::class], version = 1, exportSchema = false)
abstract class ProductoDB : RoomDatabase() {

    abstract fun productoDao(): ProductoDao

    companion object {
        @Volatile
        private var INSTANCE: ProductoDB? = null

        fun getDatabase(): ProductoDB {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = databaseBuilder(
                    TpFinal.instance.applicationContext,
                    ProductoDB::class.java,
                    Constants.DB_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }

}