package ao.covidtracking.romavicdosanjos.data.helpers

import ao.covidtracking.romavicdosanjos.data.api.ApiEndPoints
import ao.covidtracking.romavicdosanjos.data.models.CountriesCallback

class StateConfirmedByCountryHelpers(private val endPoints: ApiEndPoints) {

    suspend fun getStateConfirmedByCountryHelper(country: String): CountriesCallback {
        return endPoints.getStateConfirmedByCountry(country)
    }
}
