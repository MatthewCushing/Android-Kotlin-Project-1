package com.udacity.shoestore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.models.Shoe
import timber.log.Timber

class MainActivityViewModel: ViewModel() {
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    init {
        repeat(5) {
            if (it == 0) _shoes.value = mutableListOf(
                Shoe(
                    name = "Shoe-${it + 1}",
                    company = "Company-${it + 1}",
                    description = "Description-${it + 1}",
                    size = (it + 1).toDouble()
                )
            ) else {
                _shoes.value?.add(
                    Shoe(
                        name = "Shoe-${it + 1}",
                        company = "Company-${it + 1}",
                        description = "Description-${it + 1}",
                        size = (it + 1).toDouble()
                    )
                )
            }
        }

        Timber.i("Shoes: ${_shoes.value?.map { it.name }}")
    }

    fun addShoe(shoe: Shoe) {
        _shoes.value?.add(shoe)
    }
}