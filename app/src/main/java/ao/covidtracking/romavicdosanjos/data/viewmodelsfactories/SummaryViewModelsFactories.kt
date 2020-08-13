@file:Suppress("UNCHECKED_CAST")

package ao.covidtracking.romavicdosanjos.data.viewmodelsfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ao.covidtracking.romavicdosanjos.data.helpers.SummaryHelpers
import ao.covidtracking.romavicdosanjos.data.repositories.SummaryRepositories
import ao.covidtracking.romavicdosanjos.data.viewmodels.SummaryViewModels

class SummaryViewModelsFactories(
    private val summaryHelpers: SummaryHelpers
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SummaryViewModels::class.java) -> {
                SummaryViewModels(
                    SummaryRepositories(summaryHelpers)
                ) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}