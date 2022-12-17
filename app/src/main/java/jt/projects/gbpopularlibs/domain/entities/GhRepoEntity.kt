package jt.projects.gbpopularlibs.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Поведение дочерних сущностей при удалении родительской в onDelete. В нашем случае мы
//передаём CASCADE, чтобы дочерние сущности исчезали при удалении родительской, так как
//репозитории без пользователя сюда не подходят.
@Entity(
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GhRepoEntity(
    @SerializedName("id")
    @PrimaryKey var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("forks_count")
    var forksCount: Int,

    var userId: Int
)
