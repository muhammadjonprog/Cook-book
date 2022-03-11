package com.saidov.cookbook.modules.main.ui.model

import com.google.gson.annotations.SerializedName

import androidx.annotation.Keep


data class CategoryModelList(
    @SerializedName("drinks") var drinks: ArrayList<Drink>
)   {

    data class Drink(
        @SerializedName("strCategory") var strCategory: String,
        var isChecked : Boolean
    )
}