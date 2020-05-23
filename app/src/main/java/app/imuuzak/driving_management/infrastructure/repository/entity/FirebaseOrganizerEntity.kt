package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.Organizer
import com.google.firebase.firestore.PropertyName

data class FirebaseOrganizerEntity(
    // 名称
    @PropertyName("name")
    val name: String = "",

    // 名前（カナ）
    @PropertyName("kana")
    val kana: String = "",

    // 代表者名
    @PropertyName("representative_name")
    val representativeName: String = "",

    // 電話番号
    @PropertyName("phone_number")
    val phoneNumber: String = "",

    // メールアドレス
    @PropertyName("email")
    val email: String = "",

    // 支払い先
    @PropertyName("bank_account")
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