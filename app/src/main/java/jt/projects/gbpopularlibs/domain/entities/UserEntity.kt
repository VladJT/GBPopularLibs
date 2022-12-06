package jt.projects.gbpopularlibs.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val login: String = "noname",
    val id: Int = -1,
    val avatar_url: String? = null,
) : Parcelable