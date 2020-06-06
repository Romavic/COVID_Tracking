@file:Suppress("UNCHECKED_CAST")

package ao.covidtracking.romavicdosanjos.ui.countryDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountryDetailsFactory(
    private val country: String
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryDetailsViewModel(country) as T
    }
}