package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.Organizer
import com.google.firebase.firestore.PropertyName

data class FirebaseOrganizerEntity(
    // 名称
    val name: String = "",

    // 名前（カナ）
    val kana: String = "",

    // 代表者名
    val representativeName: String = "",

    // 電話番号
    val phoneNumber: String = "",

    // メールアドレス
    val email: String = "",

    // 支払い先
    val bankAccount: List<FirebaseBankAccountEntity>? = null
) {
    companion object {
        fun from(model: Organizer?): FirebaseOrganizerEntity {
            return FirebaseOrganizerEntity(
                name = model?.name ?: "",
                kana = model?.kana ?: "",
                representativeName = model?.representativeName ?: "",
                phoneNumber = model?.phoneNumber ?: "",
                email = model?.email ?: "",
                bankAccount = model?.bankAccount?.map { FirebaseBankAccountEntity.from(it) }
            )
        }
    }
}