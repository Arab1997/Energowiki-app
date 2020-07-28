package com.reactive.energowiki.di

import androidx.lifecycle.MutableLiveData
import com.reactive.energowiki.base.BaseViewModel
import com.reactive.energowiki.network.ErrorResp
import com.reactive.energowiki.utils.preferences.PreferenceHelper
import com.reactive.energowiki.utils.preferences.SharedManager
import com.google.gson.Gson
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val viewModelModule = module {

    fun provideMutableLiveData() = MutableLiveData<Any>()

    viewModel { BaseViewModel(get(), get(), get()) }

    single { provideMutableLiveData() }
    single(named("sharedLive")) { provideMutableLiveData() }
    single(named("errorLive")) { MutableLiveData<ErrorResp>() }
}

val networkModule = module {

    fun provideGson() = Gson()

    single { provideGson() }
}

val sharedPrefModule = module {

    factory { PreferenceHelper.customPrefs(get(), "Qweep") }

    factory { SharedManager(get(), get(), get()) }
}
