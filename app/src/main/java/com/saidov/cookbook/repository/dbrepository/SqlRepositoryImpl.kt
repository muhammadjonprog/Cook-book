package com.saidov.cookbook.repository.dbrepository


import com.saidov.cookbook.modules.main.ui.model.DrinkModel


/**
 * Created by MUHAMMADJON SAIDOV on 12,March,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
class SqlRepositoryImpl(private val appDatabase: AppDatabase) : ISqlRepository {

    override suspend fun addDrink(drinkModel: DrinkModel): Long =
        appDatabase.getDrinkDao().addDrink(drinkModel)

    override suspend fun getAllHistory(): List<DrinkModel> =
        appDatabase.getDrinkDao().getAllHistory()

    override suspend fun getAllFav(): List<DrinkModel> =
        appDatabase.getDrinkDao().getAllFav()

    override suspend fun updateFav(drinkModel: DrinkModel) =
        appDatabase.getDrinkDao().updateFav(drinkModel)

    override suspend fun delete(drinkModel: DrinkModel) =
        appDatabase.getDrinkDao().delete(drinkModel)

    override suspend fun search(query: String): List<DrinkModel> =
        appDatabase.getDrinkDao().search(query)

}