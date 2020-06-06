@file:Suppress("DEPRECATION")

package ao.covidtracking.romavicdosanjos.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.covidtracking.romavicdosanjos.R
import ao.covidtracking.romavicdosanjos.model.models.Country
import ao.covidtracking.romavicdosanjos.model.models.SummaryCallback
import ao.covidtracking.romavicdosanjos.utils.dateTimeToString

class MainFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var mainViewModel: MainViewModel
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
            mainViewModel = ViewModelProviders.of(this@MainFragment).get(MainViewModel::class.java)

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

            summaryAdapter =
                SummaryAdapter()

            countryAdapter =
                CountryAdapter(
                    context,
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

            mainViewModel.dataSummary.observe(viewLifecycleOwner,
                Observer {
                    when (it) {
                        is SummaryCallback -> {
                            progressBar.visibility = View.GONE
                            txtDateCases.visibility = View.VISIBLE
                            txtCountries.visibility = View.VISIBLE

                            summaryAdapter.addAll(it.global)
                            recyclerSummary.adapter = summaryAdapter
                            txtDateCases.text = "Information Update date: ${dateTimeToString(it.date)}"

                            countryAdapter.addAll(it.countries)
                            recyclerCountries.adapter = countryAdapter
                        }
                        else -> {
                            progressBar.visibility = View.VISIBLE
                            txtDateCases.visibility = View.GONE
                            txtCountries.visibility = View.GONE
                            Log.i("Error", "MainActivity")
                        }
                    }
                }
            )
        }
    }
}
