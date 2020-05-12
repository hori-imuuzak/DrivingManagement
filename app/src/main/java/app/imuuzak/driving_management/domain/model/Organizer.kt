package app.imuuzak.driving_management.domain.model

/**
 * 走行会主催者
 */
class Organizer(
    // 名称
    val name: String,
    // 名称（カナ）
    val kana: String,
    // 代表者名
    val representativeName: String,
    // 電話番号
    val tel: String,
    // メールアドレス
    val email: String,
    // 振込先
    val bankAccount: List<BankAccount>
)