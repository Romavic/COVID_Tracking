package ao.covidtracking.romavicdosanjos.data.repositories

import ao.covidtracking.romavicdosanjos.data.helpers.StateConfirmedByCountryHelpers
import ao.covidtracking.romavicdosanjos.data.models.CountriesCallback

class StateConfirmedByCountryRepositories(private val stateConfirmedByCountryHelpers: StateConfirmedByCountryHelpers) {

    suspend fun getStateConfirmedByCountryRepository(country: String): CountriesCallback {
        return stateConfirmedByCountryHelpers.getStateConfirmedByCountryHelper(country)
    }
}