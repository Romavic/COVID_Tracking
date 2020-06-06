package ao.covidtracking.romavicdosanjos.model.repository

import ao.covidtracking.romavicdosanjos.model.models.Countries

interface IRepository {

    fun getSummary(callback: (Any) -> Unit)

    fun getStateConfirmedByCountry(country: String, callback: (MutableList<Countries>) -> Unit)

}