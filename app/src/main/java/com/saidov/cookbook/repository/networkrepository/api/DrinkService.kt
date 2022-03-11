package com.saidov.cookbook.repository.networkrepository.api

import com.saidov.cookbook.modules.main.ui.model.CategoryModelList
import com.saidov.cookbook.modules.main.ui.model.DrinkModel
import com.saidov.cookbook.modules.main.ui.model.DrinkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by MUHAMMADJON SAIDOV on 01,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */

interface DrinkService {

    @GET("filter.php")
    suspend fun getDrinkByCategory(
        @Query("c")
        category: String
    ): Response<DrinkResponse>


    @GET("lookup.php")
    suspend fun getDrinkById(
        @Query("i")
        id: Long?
    ): Response<DrinkResponse>


    @GET("search.php")
    suspend fun searchDrinkByName(
        @Query("s")
        query: String
    ): Response<DrinkResponse>


    @GET("list.php?c=list")
    suspend fun categoryList(): Response<CategoryModelList>


    //    @GET("list.php?c=list")
//    fun getCategories(): Call<CategoryList>
}