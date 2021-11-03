package com.payback.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName="hit_table")
data class Hits(
    @PrimaryKey
    var id: Int? = null,
    var user: String? = null,
    var image: String? = null,
    var imageLarge: String? = null,
    var like: Int? = null,
    var downloads: Int? = null,
    var comments: Int? = null,
    var tags: String? = null
): Parcelable {

    fun getCapitalizeUser(): String? {
        return user?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }?:"No Username"
    }

}
