package ao.covidtracking.romavicdosanjos.model.repository

import ao.covidtracking.romavicdosanjos.model.models.Countries
import ao.covidtracking.romavicdosanjos.model.models.CountriesCallback
import ao.covidtracking.romavicdosanjos.model.models.SummaryCallback
import ao.covidtracking.romavicdosanjos.model.requestApi.ApiClient
import ao.httpstatuscode.romavicdosanjos.statusCode.SuccessfulStatusCode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository : IRepository {

    var api = ApiClient.apiEndPoint

    override fun getSummary(callback: (Any) -> Unit) {
        api.getSummary().enqueue(
            object : Callback<SummaryCallback> {
                override fun onFailure(
                    call: Call<SummaryCallback>,
                    t: Throwable
                ) {
                    callback("")
                }

                override fun onResponse(
                    call: Call<SummaryCallback>,
                    response: Response<SummaryCallback>
                ) {
                    when (response.code()) {
                        SuccessfulStatusCode.Ok -> {
                            val responseSummary = response.body()!!
                            callback(responseSummary)
                        }
                        else -> {
                            callback("")
                        }
                    }
                }
            }
        )
    }

    override fun getStateConfirmedByCountry(
        country: String,
        callback: (MutableList<Countries>) -> Unit
    ) {
        api.getStateConfirmedByCountry(country).enqueue(
            object : Callback<CountriesCallback> {
                override fun onFailure(
                    call: Call<CountriesCallback>,
                    t: Throwable
                ) {
                    callback(mutableListOf())
                }

                override fun onResponse(
                    call: Call<CountriesCallback>,
                    response: Response<CountriesCallback>
                ) {
                    when (response.code()) {
                        SuccessfulStatusCode.Ok -> {
                            val responseSummary = response.body()!!
                            callback(responseSummary)
                        }
                        else -> {
                            callback(mutableListOf())
                        }
                    }
                }
            }
        )
    }
}