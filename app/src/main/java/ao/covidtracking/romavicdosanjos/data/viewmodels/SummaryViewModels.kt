package ao.covidtracking.romavicdosanjos.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ao.covidtracking.romavicdosanjos.data.repositories.SummaryRepositories
import ao.covidtracking.romavicdosanjos.utils.Resource
import kotlinx.coroutines.Dispatchers

class SummaryViewModels(
    private val summaryRepositories: SummaryRepositories
) : ViewModel() {

    fun getSummaryViewModel() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = summaryRepositories.getSummaryRepository()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
