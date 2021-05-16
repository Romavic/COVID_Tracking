package ao.covidtracking.romavicdosanjos.data.repository

import ao.covidtracking.romavicdosanjos.data.bridge.Bridge
import ao.covidtracking.romavicdosanjos.data.models.CountriesCallback
import ao.covidtracking.romavicdosanjos.data.models.SummaryCallback

class Repository(private val bridge: Bridge) {

    suspend fun getStateConfirmedByCountryHelper(country: String): CountriesCallback {
        return bridge.getStateConfirmedByCountryHelper(country)
    }

    suspend fun getSummaryHelper(): SummaryCallback {
        return bridge.getSummaryHelper()
    }
}