@file:Suppress("DEPRECATION")

package ao.covidtracking.romavicdosanjos.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.covidtracking.romavicdosanjos.R
import ao.covidtracking.romavicdosanjos.data.api.ApiClient
import ao.covidtracking.romavicdosanjos.data.helpers.SummaryHelpers
import ao.covidtracking.romavicdosanjos.data.models.Country
import ao.covidtracking.romavicdosanjos.data.viewmodels.SummaryViewModels
import ao.covidtracking.romavicdosanjos.data.viewmodelsfactories.SummaryViewModelsFactories
import ao.covidtracking.romavicdosanjos.ui.home.adapters.CountryAdapter
import ao.covidtracking.romavicdosanjos.ui.home.adapters.SummaryAdapter
import ao.covidtracking.romavicdosanjos.utils.Status
import ao.covidtracking.romavicdosanjos.utils.dateTimeToString

class MainFragment : Fragment() {

    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var summaryViewModel: SummaryViewModels
    private lateinit var txtCountries: AppCompatTextView
    private lateinit var txtDateCases: AppCompatTextView
    private lateinit var recyclerSummary: RecyclerView
    private lateinit var recyclerCountries: RecyclerView
    private lateinit var summaryAdapter: SummaryAdapter
    private lateinit var countryAdapter: CountryAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false).apply {

            summaryViewModel = ViewModelProviders.of(
                this@MainFragment,
                SummaryViewModelsFactories(
                    SummaryHelpers(ApiClient.apiEndPoint)
                )
            )[SummaryViewModels::class.java]

            progressBar = findViewById(R.id.progressBar)
            recyclerSummary = findViewById(R.id.recyclerSummary)
            recyclerCountries = findViewById(R.id.recyclerCountries)
            txtDateCases = findViewById(R.id.txtDateCases)
            txtCountries = findViewById(R.id.txtCountries)

            recyclerSummary.layoutManager = LinearLayoutManager(
                context, RecyclerView.HORIZONTAL, false
            )

            recyclerCountries.layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            requestData()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun requestData() {
        summaryAdapter =
            SummaryAdapter()

        countryAdapter =
            CountryAdapter(
                requireContext(),
                object :
                    CountryAdapter.ICountry {
                    override fun onClick(
                        countries: Country
                    ) {
                        val data: Bundle = Bundle().apply {
                            putString("country", countries.country)
                            putInt("totalDeaths", countries.totalDeaths)
                            putInt("recovered", countries.totalRecovered)
                            putInt("totalConfirmed", countries.totalConfirmed)
                        }

                        NavHostFragment.findNavController(
                            this@MainFragment
                        ).navigate(
                            R.id.action_navigation_home_to_navigation_country_details,
                            data
                        )
                    }
                }
            )

        summaryViewModel.getSummaryViewModel().observe(viewLifecycleOwner,
            Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            progressBar.show()

                        }

                        Status.ERROR -> {
                            progressBar.hide()
                            Toast.makeText(
                                requireContext(),
                                "Error data to show..",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        Status.SUCCESS -> {
                            progressBar.hide()
                            return@let resource.data?.let { response ->
                                when {
                                    response.countries.size == 0 && response.global.toString()
                                        .isEmpty() -> {
                                        Toast.makeText(
                                            requireContext(),
                                            "No data to show..",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                    else -> {
                                        summaryAdapter.addAll(response.global)
                                        recyclerSummary.adapter = summaryAdapter
                                        txtDateCases.text =
                                            "Information Update date: ${dateTimeToString(
                                                response.date
                                            )}"
                                        countryAdapter.addAll(response.countries)
                                        recyclerCountries.adapter = countryAdapter
                                    }
                                }
                            }
                        }
                    }
                }
            }
        )
    }
}
