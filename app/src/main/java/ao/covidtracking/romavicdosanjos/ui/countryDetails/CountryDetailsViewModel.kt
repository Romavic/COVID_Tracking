package ao.covidtracking.romavicdosanjos.ui.countryDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ao.covidtracking.romavicdosanjos.model.models.Countries
import ao.covidtracking.romavicdosanjos.model.repository.Repository

class CountryDetailsViewModel(
    val country: String
) : ViewModel() {

    private var repository = Repository()

    private val _requestCountryDetails = MutableLiveData<MutableList<Countries>>().apply {
        repository.getStateConfirmedByCountry(country) {
            value = it
        }
    }

    val dataCountryDetails: LiveData<MutableList<Countries>> = _requestCountryDetails
}