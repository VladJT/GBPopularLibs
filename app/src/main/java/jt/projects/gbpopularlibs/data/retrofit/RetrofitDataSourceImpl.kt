package jt.projects.gbpopularlibs.data.retrofit

import jt.projects.gbpopularlibs.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDataSourceImpl {

    fun getApi(): GithubAPI =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
            .create(GithubAPI::class.java)

}