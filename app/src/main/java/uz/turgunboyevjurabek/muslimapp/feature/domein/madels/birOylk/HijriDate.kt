package uz.turgunboyevjurabek.muslimapp.feature.domein.madels.birOylk


import com.google.gson.annotations.SerializedName

data class HijriDate(
    @SerializedName("day")
    var day: Int?,
    @SerializedName("month")
    var month: String?
)