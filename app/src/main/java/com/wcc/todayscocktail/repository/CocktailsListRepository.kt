package com.wcc.todayscocktail.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wcc.todayscocktail.network.Cocktail
import com.wcc.todayscocktail.network.CocktailsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsListRepository() {
    // segurador de dados interno da classe
    private val cocktailListResponse = MutableLiveData<List<Cocktail>>()

    //expoe os dados retornados pelo serviço
    val  cocktailList: LiveData<List<Cocktail>>
        get() = cocktailListResponse

    init {
        getCocktailsList()
    }

    //requisição dos dados dos coqueteis atraves do retrofit
    private fun getCocktailsList() {
        //executando tarefas fora da main thread
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listResult = CocktailsApi.retrofitService.getNonAlcoholickCocktails().cocktailsList
                cocktailListResponse.postValue(listResult)

            } catch (e: Exception) {
                Log.i("service error", "${e.message}")
                withContext(Dispatchers.Main) {
                    cocktailListResponse.postValue(listOf())
                }
            }
        }
    }

}