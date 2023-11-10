package com.ifts4.tpfinal.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "nombre")
    val nombre: String?,
    @ColumnInfo(name = "descripcion")
    val descripcion: String?,
    @ColumnInfo(name = "precio")
    val precio: Double
) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),

        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel.writeDouble(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}