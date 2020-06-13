package app.imuuzak.driving_management.domain.model

import app.imuuzak.driving_management.domain.model.value.Maintenance

/**
 * 車両
 */
data class Car(
    // 名前
    val name: String,
    // エンジンオイル
    val engineOil: Maintenance,
    // ミッションオイル
    val transmissionOil: Maintenance,
    // デフオイル
    val diffOil: Maintenance,
    // オイル漏れ
    val underOilCheck: Maintenance,
    // ブレーキパッド
    val brakePad: Maintenance,
    // ブレーキローター
    val brakeRotor: Maintenance,
    // ブレーキフルード
    val brakeFluid: Maintenance,
    // タイヤ
    val tire: Maintenance,
    // タイヤ空気圧
    val tireAir: Maintenance,
    // ホイールナット
    val wheelNut: Maintenance,
    // クーラント
    val coolant: Maintenance,
    // 異音・異臭
    val someAnomaly: Maintenance
)