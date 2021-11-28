package com.la.pampa.phone.catalog.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.la.pampa.phone.catalog.core.extensions.doIfFailure
import com.la.pampa.phone.catalog.core.extensions.doIfSuccess
import com.la.pampa.phone.catalog.core.extensions.toMillis
import com.la.pampa.phone.catalog.core.plataform.SingleLiveEvent
import com.la.pampa.phone.catalog.domain.dto.PhoneDto
import com.la.pampa.phone.catalog.domain.usecases.SavePhoneUseCases
import java.time.LocalDateTime

class FormPhoneViewModel(
    private val savePhoneUseCases: SavePhoneUseCases
) : ViewModel() {

    val showLoading = SingleLiveEvent<Boolean>()
    val showError = SingleLiveEvent<Throwable>()

    private val _name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val name: MutableLiveData<String> get() = _name

    private val _description: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val description: MutableLiveData<String> get() = _description

    private val _manufacturer: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val manufacturer: MutableLiveData<String> get() = _manufacturer

    private val _price: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val price: MutableLiveData<String> get() = _price

    private val _screen: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val screen: MutableLiveData<String> get() = _screen


    private val _processor: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val processor: MutableLiveData<String> get() = _processor

    private val _ram: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val ram: MutableLiveData<String> get() = _ram

    private val valid: Boolean
        get() {
            var isValid = true
            if (name.value.isNullOrBlank()) isValid = false
            if (description.value.isNullOrBlank()) isValid = false
            if (manufacturer.value.isNullOrBlank()) isValid = false
            if (price.value.isNullOrBlank()) isValid = false
            if (screen.value.isNullOrBlank()) isValid = false
            if (processor.value.isNullOrBlank()) isValid = false
            if (ram.value.isNullOrBlank()) isValid = false

            return isValid
        }

    fun savePhone() {

        if(!valid) return

        showLoading.postValue(true)

        savePhoneUseCases.invoke(viewModelScope, PhoneDto(
            name = name.value ?: "",
            manufacturer = manufacturer.value ?: "",
            description = description.value ?: "",
            price = price.value?.toInt() ?: 0,
            imageFileName =  "",
            screen = screen.value ?: "",
            processor = processor.value ?: "",
            ram = ram.value?.toInt() ?: 0
        )
        ) { res ->
            showLoading.postValue(false)
            res.doIfSuccess {

            }
            res.doIfFailure { _, throwable ->
                showError.postValue(throwable)
            }
        }
    }
}
