package ao.covidtracking.romavicdosanjos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ao.covidtracking.romavicdosanjos.model.repository.Repository

class MainViewModel : ViewModel() {

    private var repository = Repository()

    private val _requestSummary = MutableLiveData<Any>().apply {
        repository.getSummary {
            value = it
        }
    }

    val dataSummary: LiveData<Any> = _requestSummary

}