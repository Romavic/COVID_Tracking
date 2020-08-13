@file:Suppress("UNCHECKED_CAST")

package ao.covidtracking.romavicdosanjos.data.viewmodelsfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ao.covidtracking.romavicdosanjos.data.helpers.StateConfirmedByCountryHelpers
import ao.covidtracking.romavicdosanjos.data.repositories.StateConfirmedByCountryRepositories
import ao.covidtracking.romavicdosanjos.data.viewmodels.StateConfirmedByCountryViewModels
import ao.covidtracking.romavicdosanjos.data.viewmodels.SummaryViewModels

class StateConfirmedByCountryViewModelsFactories(
    private val stateConfirmedByCountryHelpers: StateConfirmedByCountryHelpers
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(StateConfirmedByCountryViewModels::class.java) -> {
                StateConfirmedByCountryViewModels(
                    StateConfirmedByCountryRepositories(stateConfirmedByCountryHelpers)
                ) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}