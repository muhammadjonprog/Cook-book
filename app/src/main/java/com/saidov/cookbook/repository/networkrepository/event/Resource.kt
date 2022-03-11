package com.saidov.cookbook.repository.networkrepository.event

/**
 * Created by MUHAMMADJON SAIDOV on 05,March,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
sealed class Resource<T>(
val data: T?=null,
val message: String?= null,
){


    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}