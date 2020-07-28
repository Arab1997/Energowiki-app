package com.reactive.energowiki.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.reactive.energowiki.R
import com.reactive.energowiki.network.ApiInterface
import com.reactive.energowiki.network.ErrorResp
import com.reactive.energowiki.network.LoginRequest
import com.reactive.energowiki.network.RetrofitClient
import com.reactive.energowiki.utils.Constants
import com.reactive.energowiki.utils.extensions.loge
import com.reactive.energowiki.utils.extensions.logi
import com.reactive.energowiki.utils.extensions.toast
import com.reactive.energowiki.utils.network.Errors
import com.reactive.energowiki.utils.preferences.SharedManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import retrofit2.HttpException

open class BaseViewModel(
    private val gson: Gson,
    private val context: Context,
    private val sharedManager: SharedManager
) : ViewModel(), KoinComponent {

    @LayoutRes
    var parentLayoutId: Int = 0

    @LayoutRes
    var navLayoutId: Int = 0

    val data: MutableLiveData<Any> by inject()
    val shared: MutableLiveData<Any> by inject(named("sharedLive"))
    val error: MutableLiveData<ErrorResp> by inject(named("errorLive"))

    private val api = RetrofitClient
        .getRetrofit(Constants.BASE_URL, getToken(), context, gson)
        .create(ApiInterface::class.java)

    private val compositeDisposable = CompositeDisposable()

    private fun parseError(e: Throwable?) {
        var message = context.resources.getString(R.string.smth_wrong)
        if (e != null && e.localizedMessage != null) {
            loge(e.localizedMessage)
            if (e is HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                errorBody?.let {
                    try {
                        loge(it)
                        val errors = it.split(":")
                            .filter { it.contains("[") }
                        val errorsString = if (errors.isNotEmpty()) {
                            errors.toString()
                                .replace("[", "")
                                .replace(",", "\n")
                                .replace("]", "")
                                .replace("{", "")
                                .replace("}", "")
                                .replace("\"", "")
                        } else {
                            val resp = it.split(":")
                            if (resp.size >= 2) resp[1].replace("{", "")
                                .replace("}", "")
                                .replace("\"", "")
                            else it
                        }

                        message = if (errorsString.isEmpty())
                            context.resources.getString(R.string.smth_wrong)
                        else errorsString

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else message = Errors.traceErrors(e, context)
        }
        toast(context, message)
        error.value = ErrorResp(message)
    }

    fun fetchData() {

        if (sharedManager.token.isNotEmpty()) {

            logi("Current token : " + sharedManager.token)

        }
    }

    private fun getToken() = "Bearer ${sharedManager.token}"

     fun login(phone: String, password: String) = compositeDisposable.add(
        api.login(LoginRequest(phone, password)).observeAndSubscribe()
            .subscribe({
                sharedManager.token = it.access_token
                data.value = it
            }, {
                parseError(it)
            })
    )

    private fun logOut() = compositeDisposable.add(
        api.logout().observeAndSubscribe()
            .subscribe({
                sharedManager.deleteAll()
            }, {
                parseError(it)
            })
    )

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

fun <T> Single<T>.observeAndSubscribe() =
    subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
