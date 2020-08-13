package ao.covidtracking.romavicdosanjos.data.helpers

import ao.covidtracking.romavicdosanjos.data.models.SummaryCallback
import ao.covidtracking.romavicdosanjos.data.api.ApiEndPoints

class SummaryHelpers(private val endPoints: ApiEndPoints) {

    suspend fun getSummaryHelper(): SummaryCallback {
        return endPoints.getSummary()
    }
}
