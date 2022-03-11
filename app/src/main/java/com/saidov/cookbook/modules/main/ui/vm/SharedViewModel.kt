package com.saidov.cookbook.modules.main.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.saidov.cookbook.core.viewmodel.BaseViewModel
import com.saidov.cookbook.modules.main.settings.model.SettingsCategoryModel
import com.saidov.cookbook.modules.main.ui.model.CategoryModelList
import com.saidov.cookbook.modules.main.ui.model.DrinkModel
import com.saidov.cookbook.modules.main.ui.model.DrinkResponse
import com.saidov.cookbook.repository.networkrepository.event.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by MUHAMMADJON SAIDOV on 02,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */

class SharedViewModel : BaseViewModel() {

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


    private val mDrinkMutableHash = HashMap<String, MutableLiveData<Resource<DrinkResponse>>>()
    val drinkLiveDataMap: Map<String, LiveData<Resource<DrinkResponse>>> = mDrinkMutableHash

    private val mDrinkDetails = MutableLiveData<Resource<DrinkResponse>>()
    val drinkDetails: LiveData<Resource<DrinkResponse>> = mDrinkDetails


    fun addToHash(key: String) {
        if (mDrinkMutableHash[key] == null) {
            mDrinkMutableHash[key] = MutableLiveData<Resource<DrinkResponse>>()
        }
    }

    fun removeFromHash(key: String) {
        if (mDrinkMutableHash[key] != null) {
            mDrinkMutableHash.remove(key)
        }
    }




    fun categoryList() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

//        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
//            val response = network.getApi().categoryList()
//            asyncRequest(liveData = mSettingsCategory, response = response)
//        }
    }

    fun drinkByCategory(category: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val livedata = mDrinkMutableHash[category]
            val response = network.getApi().getDrinkByCategory(category = category)
            asyncRequest(liveData = livedata, response = response)

        }
    }


    fun drinkById(id: Long?) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
//            val liveData = mDrinkMutableHash[category]
            val response = network.getApi().getDrinkById(id = id)
            asyncRequest(liveData = mDrinkDetails, response = response)
        }
    }


    fun searchByDrinkName(query: String) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val livedata = mDrinkMutableHash[query]
            val response = network.getApi().searchDrinkByName(query)
            asyncRequest(liveData = livedata, response = response)
        }
    }
}
