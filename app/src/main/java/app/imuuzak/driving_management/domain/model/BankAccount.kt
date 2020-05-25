package app.imuuzak.driving_management.domain.model

import app.imuuzak.driving_management.domain.model.value.AccountType
import java.io.Serializable

/**
 * 銀行口座
 */
class BankAccount(
    // 銀行コード／名称
    val bankCode: String,
    val bankName: String,
    // 支店コード／名称
    val branchCode: String,
    val branchName: String,
    // 口座種別
    val accountType: AccountType,
    // 口座名義
    val holderName: String,
    // 口座番号
    val accountNumber: String
) : Serializable