package ao.covidtracking.romavicdosanjos.data.api

import ao.covidtracking.romavicdosanjos.data.models.CountriesCallback
import ao.covidtracking.romavicdosanjos.data.models.SummaryCallback
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoints {

    @GET("summary")
    suspend fun getSummary(): SummaryCallback

    @GET("live/country/{country}/status/confirmed")
    suspend fun getStateConfirmedByCountry(
        @Path("country") country: String
    ): CountriesCallback
}