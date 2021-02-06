package com.example.roomapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.repository.RestaurantsRepository
import com.example.roomapp.viewmodel.MRestaurantsViewModel


class MRestaurantsViewModelFactory(
    private val repository: RestaurantsRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MRestaurantsViewModel(repository) as T
    }

}