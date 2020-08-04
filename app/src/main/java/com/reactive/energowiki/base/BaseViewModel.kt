package com.reactive.energowiki.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reactive.energowiki.R
import com.reactive.energowiki.network.*
import com.reactive.energowiki.utils.extensions.loge
import com.reactive.energowiki.utils.extensions.toast
import com.reactive.energowiki.utils.network.Errors
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import retrofit2.HttpException

open class BaseViewModel(private val context: Context) : ViewModel(), KoinComponent {

    @LayoutRes
    var parentLayoutId: Int = 0

    @LayoutRes
    var fragmentLayoutId: Int = 0

    val data: MutableLiveData<Any> by inject()
    val shared: MutableLiveData<Any> by inject(named("sharedLive"))
    val error: MutableLiveData<ErrorResp> by inject(named("errorLive"))


    private val news: MutableLiveData<List<Documents>> by inject(named("documents"))
    private val documents: MutableLiveData<List<Documents>> by inject(named("documents"))
    private val organizations: MutableLiveData<List<Organizations>> by inject(named("organizations"))
    private val spravkas: MutableLiveData<List<Documents>> by inject(named("documents"))
    private val links: MutableLiveData<List<Links>> by inject(named("links"))
    private val faqs: MutableLiveData<List<Documents>> by inject(named("documents"))
    private val glossaries: MutableLiveData<List<Documents>> by inject(named("documents"))
    private val categoryApplication: MutableLiveData<List<Links>> by inject(named("links"))

    private val api: ApiInterface by inject()
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

        getDocuments()
        getNews()
        getOrganizations()
        getSpravkas()
        getLinks()
        getFaqs()
        getGlossaries()
        getCategoryApplication()
    }

    private fun getDocuments() = compositeDisposable.add(
        api.getDocuments().observeAndSubscribe()
            .subscribe({
                documents.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun getNews() = compositeDisposable.add(
        api.getNews().observeAndSubscribe()
            .subscribe({
                news.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun getOrganizations() = compositeDisposable.add(
        api.getOrganizations().observeAndSubscribe()
            .subscribe({
                organizations.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun getSpravkas() = compositeDisposable.add(
        api.getSpravkas().observeAndSubscribe()
            .subscribe({
                spravkas.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun getLinks() = compositeDisposable.add(
        api.getLinks().observeAndSubscribe()
            .subscribe({
                links.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun getFaqs() = compositeDisposable.add(
        api.getFaqs().observeAndSubscribe()
            .subscribe({
                faqs.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun getGlossaries() = compositeDisposable.add(
        api.getGlossaries().observeAndSubscribe()
            .subscribe({
                glossaries.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun getCategoryApplication() = compositeDisposable.add(
        api.getCategoryApplication().observeAndSubscribe()
            .subscribe({
                categoryApplication.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun sendAvari(address: String, text: String) = compositeDisposable.add(
        api.sendAvari(address, text).observeAndSubscribe()
            .subscribe({
                data.postValue(it)
            }, {
                parseError(it)
            })
    )

    private fun sendApplication(
        fio: String,
        text: String,
        address: String,
        phone: String,
        category_application_id: String
    ) = compositeDisposable.add(
        api.sendApplication(fio, text, address, phone, category_application_id)
            .observeAndSubscribe()
            .subscribe({
                data.postValue(it)
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
