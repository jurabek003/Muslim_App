package uz.turgunboyevjurabek.muslimapp.Model.madels.bugungilik


import com.google.gson.annotations.SerializedName

data class Bugungi(
    @SerializedName("date")
    var date: String?,
    @SerializedName("hijri_date")
    var hijriDate: HijriDate?,
    @SerializedName("region")
    var region: String?,
    @SerializedName("times")
    var times: Times?,
    @SerializedName("weekday")
    var weekday: String?
)