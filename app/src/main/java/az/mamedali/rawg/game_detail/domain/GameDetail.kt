package az.mamedali.rawg.game_detail.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetail(
    val id: Int,
    val name: String,
    val description: String?,
    val released: String?,
    val image: String?,
    val rating: Double,
    val metacritic: Int?,
    val playtime: Int,
    val platforms: List<String>,
    val esrb: String?
) : Parcelable
