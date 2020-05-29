package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.BankAccount
import com.google.firebase.firestore.PropertyName

data class FirebaseBankAccountEntity(
    // 銀行コード／名称
    val bankCode: String? = "",
    val bankName: String? = "",

    // 支店コード／名称
    val branchCode: String? = "",
    val branchName: String? = "",

    // 口座種別
    val accountType: Int? = null,

    // 口座名義
    val holderName: String? = "",

    // 口座番号
    val accountNumber: String? = ""
) {
    companion object {
        fun from(model: BankAccount?): FirebaseBankAccountEntity {
            return FirebaseBankAccountEntity(
                bankCode = model?.bankCode ?: "",
                bankName = model?.bankName ?: "",
                branchCode = model?.branchCode ?: "",
                branchName = model?.branchName ?: "",
                accountType = model?.accountType?.value(),
                holderName = model?.holderName ?: "",
                accountNumber = model?.accountNumber ?: ""
            )
        }
    }
}