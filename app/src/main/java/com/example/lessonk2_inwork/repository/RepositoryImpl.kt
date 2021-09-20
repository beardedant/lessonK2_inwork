package com.example.lessonk2_inwork.repository

import com.example.lessonk2_inwork.domen.Weather
import com.example.lessonk2_inwork.domen.getRussianCities
import com.example.lessonk2_inwork.domen.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromRemote(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocal(): Weather {
        return Weather()
    }

    override fun getListRussianCities(): List<Weather> {
        return getRussianCities()
    }

    override fun getListWorldCities(): List<Weather> {
        return getWorldCities()
    }
}