package az.mamedali.rawg.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val rating: Double
) : Parcelable