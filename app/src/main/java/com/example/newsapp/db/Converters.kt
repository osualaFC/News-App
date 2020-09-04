package com.example.newsapp.db

import androidx.room.TypeConverter
import com.example.newsapp.models.Source


/**this converter is needed cos Room uses only private data, other forms of data have to be converted to primitive**/
class Converters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String): Source {
        return Source(name, name)
    }
}