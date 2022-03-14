package com.saidov.cookbook.repository.dbrepository


import androidx.room.*
import com.saidov.cookbook.modules.main.ui.model.DrinkModel


/**
 * Created by MUHAMMADJON SAIDOV on 01,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
//
@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDrink(drinkModel: DrinkModel): Long

    @Query("SELECT * FROM drink")
    fun getAllHistory(): List<DrinkModel>

    @Query("SELECT * FROM drink WHERE isFavorite = 1")
    fun getAllFav(): List<DrinkModel>

    @Query("SELECT * FROM drink WHERE strDrink  LIKE  '%'|| :query ||'%'")
    fun search(query: String): List<DrinkModel>

    @Update
    suspend fun updateFav(drinkModel: DrinkModel)

    @Delete
    suspend fun delete(drinkModel: DrinkModel)

}