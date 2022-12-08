package jt.projects.gbpopularlibs.data.users

import com.google.gson.GsonBuilder
import jt.projects.gbpopularlibs.interfaces.CommonCallback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


open class BaseRetrofit(val baseUrl: String, private val apiKey: String = "") {

    inline fun <reified T> getRetrofitImpl(): T {
        val podRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().setLenient().create())
            )
            .client(createOkHttpClient(PODInterceptor()))
            .build()
        return podRetrofit.create(T::class.java)
    }

    fun <T> isApiKeyGood(callback: CommonCallback<T>): Boolean =
        if (apiKey.isBlank()) {
            callback.onFailure(Throwable("You need API key"))
            false
        } else true


    fun <T> getCallbackFromRetrofit(callback: CommonCallback<T>): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onSuccess(response.body()!!)
                } else {
                    val message = response.message()
                    if (message.isNullOrEmpty()) {
                        callback.onFailure(IOException("Error: message is Null Or Empty"))
                    } else {
                        callback.onFailure(Throwable(message))
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onFailure(t)
            }
        }
    }

    fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        )
        return httpClient.build()
    }

    //В библиотеку можно внедрить перехватчики для изменения заголовков при помощи класса Interceptor из OkHttp.
    // Сначала следует создать объект перехватчика и передать его в OkHttp, который в свою очередь следует явно подключить в
    // Retrofit.Builder через метод client().
    inner class PODInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}