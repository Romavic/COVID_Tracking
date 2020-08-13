package ao.covidtracking.romavicdosanjos.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ao.covidtracking.romavicdosanjos.data.repositories.StateConfirmedByCountryRepositories
import ao.covidtracking.romavicdosanjos.utils.Resource
import kotlinx.coroutines.Dispatchers

class StateConfirmedByCountryViewModels(
    private val stateConfirmedByCountryRepositories: StateConfirmedByCountryRepositories
) : ViewModel() {

    fun getStateConfirmedByCountryViewModel(country: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = stateConfirmedByCountryRepositories.getStateConfirmedByCountryRepository(
                        country
                    )
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
