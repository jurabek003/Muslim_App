package uz.turgunboyevjurabek.muslimapp.Model.madels.birHaftalik


import com.google.gson.annotations.SerializedName

data class HijriDate(
    @SerializedName("day")
    var day: Int?,
    @SerializedName("month")
    var month: String?
)