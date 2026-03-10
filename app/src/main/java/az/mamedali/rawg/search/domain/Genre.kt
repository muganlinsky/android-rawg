package az.mamedali.rawg.search.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageBackground: String?
) : Parcelable