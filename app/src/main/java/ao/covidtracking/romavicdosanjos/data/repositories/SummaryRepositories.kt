package ao.covidtracking.romavicdosanjos.data.repositories

import ao.covidtracking.romavicdosanjos.data.helpers.SummaryHelpers
import ao.covidtracking.romavicdosanjos.data.models.SummaryCallback

class SummaryRepositories(private val summaryHelpers: SummaryHelpers) {

    suspend fun getSummaryRepository(): SummaryCallback {
        return summaryHelpers.getSummaryHelper()
    }
}