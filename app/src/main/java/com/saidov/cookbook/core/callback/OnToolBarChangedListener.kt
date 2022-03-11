package com.saidov.cookbook.core.callback

import androidx.annotation.StringRes

/**
 * Created by MUHAMMADJON SAIDOV on 01,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
interface OnToolBarChangedListener {

    fun setToolbarName(title: String)

    fun setToolBarBackVisibility(status:Boolean)

    fun clearToolBar()
}