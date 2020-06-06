package ao.covidtracking.romavicdosanjos.model.requestApi

import ao.covidtracking.romavicdosanjos.model.models.CountriesCallback
import ao.covidtracking.romavicdosanjos.model.models.SummaryCallback
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoints {

    //GET Summary
    @GET("summary")
    fun getSummary(): Call<SummaryCallback>

    //GET State Confirmed By Country
    @GET("live/country/{country}/status/confirmed")
    fun getStateConfirmedByCountry(
        @Path("country") country: String
    ): Call<CountriesCallback>

}