package uz.turgunboyevjurabek.muslimapp.Model.madels.birOylk


import com.google.gson.annotations.SerializedName

data class BirOylikItem(
    @SerializedName("date")
    var date: String?,
    @SerializedName("day")
    var day: Int?,
    @SerializedName("hijri_date")
    var hijriDate: HijriDate?,
    @SerializedName("month")
    var month: Int?,
    @SerializedName("region")
    var region: String?,
    @SerializedName("regionNumber")
    var regionNumber: Int?,
    @SerializedName("times")
    var times: Times?,
    @SerializedName("weekday")
    var weekday: String?
)