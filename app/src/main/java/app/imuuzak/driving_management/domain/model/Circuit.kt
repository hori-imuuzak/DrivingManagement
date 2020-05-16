package app.imuuzak.driving_management.domain.model

import android.webkit.URLUtil
import java.lang.IllegalArgumentException

/**
 * サーキット場
 */
data class Circuit(
    // 名称
    val name: String = "",
    // 名称（カナ）
    val kana: String = "",
    // URL
    val url: String = ""
) {
    init {
        if (name.isEmpty()) {
            throw IllegalArgumentException("name")
        }

        if (kana.isEmpty() or kana.matches("^[\\u30A0-\\u30FF]+$".toRegex()).not()) {
            throw IllegalArgumentException("kana")
        }

        if (url.isNotEmpty() and URLUtil.isValidUrl(url).not()) {
            throw IllegalArgumentException("url")
        }
    }
}