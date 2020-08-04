package com.reactive.energowiki.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.reactive.energowiki.base.BaseViewModel
import com.reactive.energowiki.utils.preferences.PreferenceHelper
import com.reactive.energowiki.utils.preferences.SharedManager
import com.google.gson.Gson
import com.reactive.energowiki.network.*
import com.reactive.energowiki.utils.Constants
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val viewModelModule = module {

    fun provideMutableLiveData() = MutableLiveData<Any>()

    viewModel { BaseViewModel(get()) }

    single { provideMutableLiveData() }
    single(named("sharedLive")) { provideMutableLiveData() }
    single(named("errorLive")) { MutableLiveData<ErrorResp>() }

    single(named("links")) { MutableLiveData<List<Links>>() }
    single(named("documents")) { MutableLiveData<List<Documents>>() }
    single(named("organizations")) { MutableLiveData<List<Organizations>>() }
}

val networkModule = module {

    fun provideGson() = Gson()

    fun provideApi(context: Context, gson: Gson) = RetrofitClient
        .getRetrofit(Constants.BASE_URL, context, gson)
        .create(ApiInterface::class.java)

    single { provideGson() }

    single { provideApi(get(), get()) }
}

val sharedPrefModule = module {

    factory { PreferenceHelper.customPrefs(get(), "Qweep") }

    factory { SharedManager(get(), get(), get()) }
}
