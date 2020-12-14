package com.udacity.shoestore.ui

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.models.Shoe
import timber.log.Timber

class MainActivityViewModel: ViewModel(), Observable {
    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    private val _isShoeAdded = MutableLiveData<Boolean>()

    @Bindable
    val name = MutableLiveData<String>()
    @Bindable
    val company = MutableLiveData<String>()
    @Bindable
    val size = MutableLiveData<String>()
    @Bindable
    val description = MutableLiveData<String>()

    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes
    val isShoeAdded: LiveData<Boolean>
        get() = _isShoeAdded

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

        _isShoeAdded.value = false

        Timber.i("Shoes: ${_shoes.value?.map { it.name }}")
    }

    fun addShoe() {
        val nameDefined = when(name.value == "" || name.value == null) {
            true -> "N/A"
            false -> name.value!!
        }

        val companyDefined = when(company.value == "" || company.value == null) {
            true -> "N/A"
            false -> company.value!!
        }

        val sizeDefined = when(size.value == "" || size.value == null) {
            true -> 0.0
            false -> size.value.toString().toDouble()!!
        }

        val descriptionDefined = when(description.value == "" || description.value == null) {
            true -> "N/A"
            false -> description.value!!
        }

        _shoes.value?.add(Shoe(nameDefined, sizeDefined, companyDefined, descriptionDefined))
        _isShoeAdded.value = true
    }

    fun shoeAddedComplete() {
        _isShoeAdded.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) { }
}