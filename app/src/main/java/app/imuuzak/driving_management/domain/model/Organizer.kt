package app.imuuzak.driving_management.domain.model

import android.util.Patterns
import java.io.Serializable
import java.lang.IllegalArgumentException

/**
 * 走行会主催者
 */
class Organizer(
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
    // 振込先
    val bankAccount: List<BankAccount> = listOf()
) : Serializable {
    init {
        if (name.isEmpty()) {
            throw IllegalArgumentException("name")
        }

        if (kana.isNotEmpty() && kana.matches("^[\\u30A0-\\u30FF]+$".toRegex()).not()) {
            throw IllegalArgumentException("kana")
        }

        if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            throw IllegalArgumentException("email")
        }
    }
}