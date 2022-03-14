package com.saidov.cookbook.repository.networkrepository.event

/**
 * Created by MUHAMMADJON SAIDOV on 14,March,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
data class Event<out T>(val status: Status, val data: T?, val error: Error?) {

    companion object {
        fun <T> loading(): Event<T> {
            return Event(Status.LOADING, null, null)
        }

        fun <T> success(data: T?): Event<T> {
            return Event(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Error?): Event<T> {
            return Event(Status.ERROR, null,error)
        }
    }
}




