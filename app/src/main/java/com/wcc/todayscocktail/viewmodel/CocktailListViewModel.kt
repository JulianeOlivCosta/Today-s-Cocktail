package com.wcc.todayscocktail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wcc.todayscocktail.network.Cocktail
import com.wcc.todayscocktail.repository.CocktailsListRepository

class CocktailListViewModel(private val repository: CocktailsListRepository): ViewModel() {
    val  cocktailList: LiveData<List<Cocktail>>
        get() = repository.cocktailList
}
