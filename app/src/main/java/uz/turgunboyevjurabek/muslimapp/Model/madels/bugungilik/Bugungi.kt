package uz.turgunboyevjurabek.muslimapp.Model.madels.bugungilik


import com.google.gson.annotations.SerializedName

data class Bugungi(
    @SerializedName("date")
    var date: String?=null,
    @SerializedName("hijri_date")
    var hijriDate: HijriDate?=null,
    @SerializedName("region")
    var region: String?=null,
    @SerializedName("times")
    var times: Times?=null,
    @SerializedName("weekday")
    var weekday: String?=null
)