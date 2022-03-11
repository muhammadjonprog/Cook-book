package com.saidov.cookbook.modules.main.settings.model
import com.google.gson.annotations.SerializedName


data class SettingsCategoryModel(
    @SerializedName("strCategory")
    var strCategory: String,
    var isChecked : Boolean
)

