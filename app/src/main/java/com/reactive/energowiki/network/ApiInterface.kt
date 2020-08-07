package com.reactive.energowiki.network

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @POST("documents")
    fun getDocuments(): Single<List<Documents>>

    @POST("news")
    fun getNews(): Single<List<Documents>>

    @POST("organizations")
    fun getOrganizations(): Single<List<Documents>>

    @POST("spravkas")
    fun getSpravkas(): Single<List<Documents>>

    @POST("links")
    fun getLinks(): Single<List<Documents>>

    @POST("faqs")
    fun getFaqs(): Single<List<Documents>>

    @POST("glosarrys")
    fun getGlossaries(): Single<List<Documents>>

    @POST("category-application")
    fun getCategoryApplication(): Single<List<Documents>>

    @POST("avari")
    @FormUrlEncoded
    fun sendAvari(
        @Field("address") address: String,
        @Field("text") text: String
    ): Single<StatusResp>

    @POST("avari")
    @FormUrlEncoded
    fun sendApplication(
        @Field("fio") fio: String,
        @Field("text") text: String,
        @Field("address") address: String,
        @Field("phone") phone: String,
        @Field("category_application_id") category_application_id: String
    ): Single<StatusResp>

}

data class ErrorResp(val message: String, val errors: Any? = null)

data class StatusResp(val status: String)

data class Documents(
    val date: String,
    val url: String,
    val id: String,
    val number: String,
    val status: String,
    val text_ru: String,
    val text_uz: String,
    val title_ru: String,
    val title_uz: String,
    val address_ru: String,
    val address_uz: String,
    val email: String,
    val latitude: String,
    val longitude: String,
    val name_ru: String,
    val name_uz: String,
    val phone: String,
    val work_time_ru: String,
    val work_time_uz: String,
    val ws: String
)
