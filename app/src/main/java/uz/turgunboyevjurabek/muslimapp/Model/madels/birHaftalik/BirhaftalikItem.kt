package uz.turgunboyevjurabek.muslimapp.Model.madels.birHaftalik


import com.google.gson.annotations.SerializedName

data class BirhaftalikItem(
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