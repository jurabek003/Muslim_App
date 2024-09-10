package uz.turgunboyevjurabek.muslimapp.feature.domein.madels.timeConverter

import java.util.Date

import androidx.room.TypeConverter
class DateConverter {

    @TypeConverter
    fun toDate(date: Long?): Date? {
        return date?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}