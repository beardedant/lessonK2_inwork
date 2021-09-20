package com.example.lessonk2_inwork.repository

import com.example.lessonk2_inwork.domen.Weather

interface Repository {
    fun getWeatherFromRemote() : Weather
    fun getWeatherFromLocal() : Weather

    fun getListRussianCities() : List<Weather>
    fun getListWorldCities() : List<Weather>
}