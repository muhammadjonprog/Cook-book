package com.saidov.cookbook.repository.networkrepository.repo

import com.saidov.cookbook.repository.networkrepository.api.CookService


/**
 * Created by MUHAMMADJON SAIDOV on 31,январь,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
interface INetworkRepository {

    fun getApi(): CookService

}