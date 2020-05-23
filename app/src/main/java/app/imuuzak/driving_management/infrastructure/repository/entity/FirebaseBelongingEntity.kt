package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.Belonging
import com.google.firebase.firestore.PropertyName

data class FirebaseBelongingEntity(
    // 名称
    @PropertyName("name")
    val name: String = "",

    // 個数
    @PropertyName("count")
    val count: Int = 0
) {
    companion object {
        fun from(model: Belonging?): FirebaseBelongingEntity {
            return FirebaseBelongingEntity(
                name = model?.name ?: "",
                count = model?.count ?: 0
            )
        }
    }
}