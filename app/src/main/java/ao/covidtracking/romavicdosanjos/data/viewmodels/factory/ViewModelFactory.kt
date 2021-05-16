@file:Suppress("UNCHECKED_CAST")

package ao.covidtracking.romavicdosanjos.data.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ao.covidtracking.romavicdosanjos.data.bridge.Bridge
import ao.covidtracking.romavicdosanjos.data.repository.Repository
import ao.covidtracking.romavicdosanjos.data.viewmodels.StateConfirmedByCountryViewModels
import ao.covidtracking.romavicdosanjos.data.viewmodels.SummaryViewModels

class ViewModelFactory(private val bridge: Bridge) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SummaryViewModels::class.java) -> {
                SummaryViewModels(Repository(bridge)) as T
            }
            modelClass.isAssignableFrom(StateConfirmedByCountryViewModels::class.java) -> {
                StateConfirmedByCountryViewModels(Repository(bridge)) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}