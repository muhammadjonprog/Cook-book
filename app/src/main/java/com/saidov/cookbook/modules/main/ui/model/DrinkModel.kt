package com.saidov.cookbook.modules.main.ui.model

import com.google.gson.annotations.SerializedName

/**
 * Created by MUHAMMADJON SAIDOV on 04,March,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */

data class DrinkModel(
    @SerializedName("idDrink") var idDrink: Long?,
    @SerializedName("strAlcoholic") var strAlcoholic: String,
    @SerializedName("strCategory") var strCategory: String,
    @SerializedName("strCreativeCommonsConfirmed") var strCreativeCommonsConfirmed: String,
    @SerializedName("strDrink") var strDrink: String,
    @SerializedName("strDrinkAlternate") var strDrinkAlternate: String?,
    @SerializedName("strDrinkThumb") var strDrinkThumb: String, // https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg
    @SerializedName("strGlass") var strGlass: String,
    @SerializedName("strIBA") var strIBA: String?,
    @SerializedName("strImageAttribution") var strImageAttribution: String?,
    @SerializedName("strImageSource") var strImageSource: String?, // https://commons.wikimedia.org/wiki/File:Klassiche_Margarita.jpg
    @SerializedName("strTags") var strTags: String?,
    @SerializedName("strInstructions") var strInstructions: String?,
    @SerializedName("strVideo") var strVideo: String?,
    @SerializedName("strIngredient1") var strIngredient1: String?,
    @SerializedName("strIngredient2") var strIngredient2: String?,
    @SerializedName("strIngredient3") var strIngredient3: String?,
    @SerializedName("strIngredient4") var strIngredient4: String?,
    @SerializedName("strIngredient5") var strIngredient5: String?,
    @SerializedName("strMeasure1") var strMeasure1: String?,
    @SerializedName("strMeasure2") var strMeasure2: String?,
    @SerializedName("strMeasure3") var strMeasure3: String?,

    @SerializedName("dateModified") var dateModified: String?
)