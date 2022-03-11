package com.saidov.cookbook.modules.main.ui.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep


data class DrinkResponse(
    @SerializedName("drinks")
    var list: MutableList<DrinkModel> = ArrayList()
)

