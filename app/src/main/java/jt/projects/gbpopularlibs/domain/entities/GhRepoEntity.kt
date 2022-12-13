package jt.projects.gbpopularlibs.domain.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: Int,
    var userId: Int
)
