package ao.covidtracking.romavicdosanjos.data.bridge

import ao.covidtracking.romavicdosanjos.data.api.ApiClient
import ao.covidtracking.romavicdosanjos.data.models.CountriesCallback
import ao.covidtracking.romavicdosanjos.data.models.SummaryCallback

class Bridge(private val apiClient: ApiClient) {

    suspend fun getStateConfirmedByCountryHelper(country: String): CountriesCallback {
        return apiClient.getStateConfirmedByCountry(country)
    }

    suspend fun getSummaryHelper(): SummaryCallback {
        return apiClient.getSummary()
    }
}