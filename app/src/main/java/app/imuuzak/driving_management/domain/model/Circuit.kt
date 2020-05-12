package app.imuuzak.driving_management.domain.model

/**
 * サーキット場
 */
data class Circuit(
    // 名称
    val name: String,
    // 名称（カナ）
    val kana: String,
    // 都道府県
    val state: String,
    // 市区町村
    val city: String,
    // 町名
    val town: String,
    // 番地
    val address1: String,
    // 建物名
    val buildingName: String
)