package com.saidov.cookbook.modules.main.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.saidov.cookbook.core.viewmodel.BaseViewModel
import com.saidov.cookbook.modules.main.settings.model.SettingsCategoryModel
import com.saidov.cookbook.modules.main.ui.model.DrinkModel
import com.saidov.cookbook.modules.main.ui.model.DrinkResponse
import com.saidov.cookbook.repository.networkrepository.event.Event

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by MUHAMMADJON SAIDOV on 02,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */

class SharedViewModel : BaseViewModel() {

    private val mAll = MutableLiveData<List<DrinkModel>>()
    val all: LiveData<List<DrinkModel>> = mAll


    private val mSettingsCategory = MutableLiveData<ArrayList<SettingsCategoryModel>>().apply {
        val list: ArrayList<SettingsCategoryModel> = ArrayList()
        val ordinaryDrink = SettingsCategoryModel("Ordinary Drink", true)
        list.add(ordinaryDrink)
        val coffee = SettingsCategoryModel("Coffee / Tea", true)
        list.add(coffee)
        val cocktail = SettingsCategoryModel("Cocktail", true)
        list.add(cocktail)
        val shake = SettingsCategoryModel("Shake", true)
        list.add(shake)
        val cocoa = SettingsCategoryModel("Cocoa", true)
        list.add(cocoa)
        val shot = SettingsCategoryModel("Shot", true)
        list.add(shot)
        val homemadeLiqueur = SettingsCategoryModel("Homemade Liqueur", true)
        list.add(homemadeLiqueur)
        val beer = SettingsCategoryModel("Beer", true)
        list.add(beer)

        value = list
    }
    var settingsCategory: MutableLiveData<ArrayList<SettingsCategoryModel>> = mSettingsCategory


    private val mDrinkMutableHash = HashMap<String, MutableLiveData<Event<DrinkResponse>>>()
    val drinkLiveDataMap: Map<String, LiveData<Event<DrinkResponse>>> = mDrinkMutableHash


    private val mDrinkDetails = MutableLiveData<Event<DrinkResponse>>()
    val drinkDetails: LiveData<Event<DrinkResponse>> = mDrinkDetails


    fun addToHash(key: String) {
        if (mDrinkMutableHash[key] == null) {
            mDrinkMutableHash[key] = MutableLiveData<Event<DrinkResponse>>()
        }
    }

    fun removeFromHash(key: String) {
        if (mDrinkMutableHash[key] != null) {
            mDrinkMutableHash.remove(key)
        }
    }


    fun drinkByCategory(category: String) {
        val liveData = mDrinkMutableHash[category]
        asyncRequest(liveData) {
                network.getApi().getDrinkByCategory(category)
        }
    }


    fun drinkById(id: Long?) {
        asyncRequest(mDrinkDetails) {
            network.getApi().getDrinkById(id = id)
        }
    }


    fun searchByDrinkName(query: String, category: String) {
        val livedata = mDrinkMutableHash[category]
        asyncRequest(livedata) {
            network.getApi().searchDrinkByName(query)
        }
    }

    fun add(drinkModel: DrinkModel) {
        viewModelScope.launch {
            db.addDrink(drinkModel)
        }
    }

    fun loadHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = db.getAllHistory()
            mAll.postValue(result)
        }
    }

    fun loadFav() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = db.getAllFav()
            mAll.postValue(result)
        }
    }



    fun updateFav(drinkModel: DrinkModel) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateFav(drinkModel)
            loadFav()
        }
    }




    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = db.search(query)
            mAll.postValue(result)
        }
    }


    fun delete(drinkModel: DrinkModel) {
        viewModelScope.launch(Dispatchers.IO) {
            db.delete(drinkModel)
            loadHistory()
        }
    }
}
