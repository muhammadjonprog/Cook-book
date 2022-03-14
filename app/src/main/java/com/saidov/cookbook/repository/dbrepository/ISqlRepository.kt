package com.saidov.cookbook.repository.dbrepository


import com.saidov.cookbook.modules.main.ui.model.DrinkModel

/**
 * Created by MUHAMMADJON SAIDOV on 12,March,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
interface ISqlRepository {

    suspend fun addDrink(drinkModel: DrinkModel) : Long

    suspend fun getAllHistory(): List<DrinkModel>

    suspend fun getAllFav(): List<DrinkModel>

    suspend fun updateFav(drinkModel: DrinkModel)

    suspend fun delete(drinkModel: DrinkModel)

    suspend fun search(query : String): List<DrinkModel>
}