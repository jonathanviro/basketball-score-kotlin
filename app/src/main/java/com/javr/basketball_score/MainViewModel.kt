package com.javr.basketball_score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _localScoreLiveData: MutableLiveData<Int> = MutableLiveData()
    private var _visitorScoreLiveData:MutableLiveData<Int> = MutableLiveData()

    val localScoreLiveData: LiveData<Int>
        get() = _localScoreLiveData

    val visitorScore: LiveData<Int>
        get() = _visitorScoreLiveData

    init {
        resetScores()
    }

    fun resetScores() {
        _localScoreLiveData.value = 0
        _visitorScoreLiveData.value = 0
    }

    fun addPoints(booIsLocal: Boolean, intPuntos: Int) {
        if(booIsLocal) {
            _localScoreLiveData.value = _localScoreLiveData.value!! + intPuntos
        }else{
            _visitorScoreLiveData.value = _visitorScoreLiveData.value?.plus(intPuntos)
        }
    }

    fun minusPoints(booIsLocal: Boolean, intPuntos: Int) {
        if(booIsLocal) {
            if (_localScoreLiveData.value!! > 0){
                _localScoreLiveData.value =  _localScoreLiveData.value!! - intPuntos
            }
        }else{
            if (_visitorScoreLiveData.value!! > 0) {
                _visitorScoreLiveData.value = _visitorScoreLiveData.value!! - intPuntos
            }
        }
    }
}