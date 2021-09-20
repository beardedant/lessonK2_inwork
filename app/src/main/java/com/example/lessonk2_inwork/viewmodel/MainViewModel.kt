package com.example.lessonk2_inwork.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lessonk2_inwork.domen.Weather
import com.example.lessonk2_inwork.repository.RepositoryImpl
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataToObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveDataToObserver() = liveDataToObserver

    fun getDataFromLocalSourceRussian() {
        getDataFromLocalSource(true)
    }

    fun getDataFromLocalSourceWorld() {
        getDataFromLocalSource(false)
    }

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserver.postValue(AppState.Loading)
        Thread {
            sleep(300)
            when {
                isRussian -> getLiveDataToObserver().postValue(AppState.Success(repository.getListRussianCities()))
                !isRussian -> getLiveDataToObserver().postValue(AppState.Success(repository.getListWorldCities()))
                else -> getLiveDataToObserver().postValue(AppState.Error(IllegalStateException()))
            }
        }.start()
    }
}