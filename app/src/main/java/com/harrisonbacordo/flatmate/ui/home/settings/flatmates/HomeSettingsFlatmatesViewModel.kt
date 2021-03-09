package com.harrisonbacordo.flatmate.ui.home.settings.flatmates

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harrisonbacordo.flatmate.data.models.User
import com.harrisonbacordo.flatmate.data.repositories.FlatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeSettingsFlatmatesViewModel @ViewModelInject constructor(private val flatRepository: FlatRepository): ViewModel() {
    var flatMates: List<User> by mutableStateOf(emptyList())
        private set

    var flatId: String by mutableStateOf("")
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            flatMates = flatRepository.getFlatmates()
            flatId = flatRepository.getFlatId()
        }
    }
}