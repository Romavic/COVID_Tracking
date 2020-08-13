package ao.covidtracking.romavicdosanjos.data.api

import ao.covidtracking.romavicdosanjos.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .baseUrl(BuildConfig.ApiUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiEndPoint: ApiEndPoints = retrofit.create(
        ApiEndPoints::class.java
    )
}