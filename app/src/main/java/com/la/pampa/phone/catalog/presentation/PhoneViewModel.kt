package com.la.pampa.phone.catalog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.la.pampa.phone.catalog.core.extensions.doIfFailure
import com.la.pampa.phone.catalog.core.extensions.doIfSuccess
import com.la.pampa.phone.catalog.core.plataform.SingleLiveEvent
import com.la.pampa.phone.catalog.domain.dto.PhoneDto
import com.la.pampa.phone.catalog.domain.models.Phone
import com.la.pampa.phone.catalog.domain.usecases.GetPhonesUseCases
import com.la.pampa.phone.catalog.domain.usecases.SavePhoneUseCases
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhoneViewModel(
    private val getPhonesUseCases: GetPhonesUseCases
) : ViewModel() {

    val showLoading = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<Throwable>()

    private val _phones: MutableLiveData<List<Phone>> by lazy {
        MutableLiveData<List<Phone>>()
    }
    val phones: LiveData<List<Phone>> = _phones

    fun getPhones() {
        showLoading.postValue(true)
        viewModelScope.launch {
            getPhonesUseCases.invoke().collect {
                _phones.value = it
            }
        }

    /*{ res ->
            showLoading.postValue(false)
            res.doIfSuccess {
                _phones.value = it
                print("")
            }
            res.doIfFailure { _, throwable ->
                showError.postValue(throwable)
            }
        }*/
    }



}