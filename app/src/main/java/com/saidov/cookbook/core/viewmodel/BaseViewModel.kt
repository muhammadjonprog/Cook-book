package com.saidov.cookbook.core.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saidov.cookbook.repository.dbrepository.ISqlRepository
import com.saidov.cookbook.repository.networkrepository.event.Event
import com.saidov.cookbook.repository.networkrepository.repo.INetworkRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Created by MUHAMMADJON SAIDOV on 01,март,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
abstract class BaseViewModel : ViewModel(), KoinComponent {
    protected val network: INetworkRepository by inject()
    protected val db: ISqlRepository by inject()


    fun <T> asyncRequest(
        liveData: MutableLiveData<Event<T>>?,
        request: suspend () -> Response<T>
    ) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        liveData?.postValue(Event.loading())

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler ) {
            try {
                val response = request.invoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveData?.postValue(Event.success(it))
                    }
                }
            } catch (t: Throwable) {
                when (t) {
//                    is SocketTimeoutException -> liveData?.value = Event.error(null,"Тайм аут!")
//                    is ConnectException -> liveData?.value = Event.error(null,"Ошибка подключения!")
//                    is IOException -> liveData?.value = Event.error(null,"Нет подключение к интернету!")

                }
            }
        }
    }

}