package jt.projects.gbpopularlibs.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class UserEntity(
    val login: String = "no_name",
    @PrimaryKey val id: Int = -1,
    val avatar_url: String? = null,
    val repos_url: String? = null
) : Parcelable