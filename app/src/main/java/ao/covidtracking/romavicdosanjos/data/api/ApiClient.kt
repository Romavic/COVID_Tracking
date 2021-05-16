package ao.covidtracking.romavicdosanjos.data.api

import ao.covidtracking.romavicdosanjos.BuildConfig
import ao.covidtracking.romavicdosanjos.data.models.CountriesCallback
import ao.covidtracking.romavicdosanjos.data.models.ModelsCountry
import ao.covidtracking.romavicdosanjos.data.models.SummaryCallback
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface ApiClient {

    object RetrofitBuilder {
        private val logger by lazy {
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            }
        }

        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.ApiUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val endPoints: ApiClient = getRetrofit().create(ApiClient::class.java)
    }

    @GET("countries")
    suspend fun getCountries(): ModelsCountry

    @GET("summary")
    suspend fun getSummary(): SummaryCallback

    @GET("live/country/{country}/status/confirmed")
    suspend fun getStateConfirmedByCountry(
        @Path("country") country: String
    ): CountriesCallback

}