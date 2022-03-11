package com.saidov.cookbook.core.di

import com.saidov.cookbook.modules.main.ui.vm.SharedViewModel
import com.saidov.cookbook.repository.networkrepository.repo.INetworkRepository
import com.saidov.cookbook.repository.networkrepository.repo.NetworkRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by MUHAMMADJON SAIDOV on 01,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
val vmModule = module {
    viewModel {
        SharedViewModel()
    }
}

val repositoryModule = module {
//    single<ISqlRepository> {
//        SqlRepositoryImpl(context = get())
//    }

    single<INetworkRepository> {
        NetworkRepositoryImpl()
    }
}