package jt.projects.gbpopularlibs.data.retrofit

import jt.projects.gbpopularlibs.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class RetrofitDataSourceImpl {

    fun getApi(): GithubAPI = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        //  .client(createOkHttpClient(MyInterceptor()))
        .build().create(GithubAPI::class.java)


    /**
     *     В библиотеку можно внедрить перехватчики для изменения заголовков при помощи класса Interceptor из OkHttp.
    Сначала следует создать объект перехватчика и передать его в OkHttp, который в свою очередь следует явно подключить в
    Retrofit.Builder через метод client().
     */
    private fun createOkHttpClient(interceptor: Interceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

    inner class MyInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }

}
