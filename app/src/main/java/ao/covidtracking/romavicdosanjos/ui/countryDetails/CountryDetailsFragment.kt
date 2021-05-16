@file:Suppress("DEPRECATION")

package ao.covidtracking.romavicdosanjos.ui.countryDetails

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.covidtracking.romavicdosanjos.R
import ao.covidtracking.romavicdosanjos.data.api.ApiClient
import ao.covidtracking.romavicdosanjos.data.bridge.Bridge
import ao.covidtracking.romavicdosanjos.data.models.Countries
import ao.covidtracking.romavicdosanjos.data.viewmodels.StateConfirmedByCountryViewModels
import ao.covidtracking.romavicdosanjos.data.viewmodels.factory.ViewModelFactory
import ao.covidtracking.romavicdosanjos.ui.countryDetails.adpaters.CountryDetailsAdapter
import ao.covidtracking.romavicdosanjos.utils.Status

class CountryDetailsFragment : Fragment() {

    private lateinit var txtCountry: AppCompatTextView
    private lateinit var stateConfirmedByCountryViewModel: StateConfirmedByCountryViewModels
    private lateinit var adapterCountryDetails: CountryDetailsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ContentLoadingProgressBar
    private lateinit var alertDialog: AlertDialog

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_country, container, false).apply {
            val country = requireArguments().getString("country")

            stateConfirmedByCountryViewModel = ViewModelProviders.of(
                this@CountryDetailsFragment,
                ViewModelFactory(Bridge(ApiClient.RetrofitBuilder.endPoints))
            )[StateConfirmedByCountryViewModels::class.java]

            txtCountry = findViewById(R.id.txtCountry)
            recyclerView = findViewById(R.id.recyclerCountryDetails)
            progressBar = findViewById(R.id.progressCountryDetails)

            txtCountry.text = "Country: $country"

            recyclerView.layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )

            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            requestData(country)
        }
    }

    private fun requestData(country: String?) {
        adapterCountryDetails = CountryDetailsAdapter(
            requireContext(), object : CountryDetailsAdapter.ICountryDetails {
                override fun onClick(countries: Countries) {
                    alertDialog = AlertDialog.Builder(requireContext()).create()
                    alertDialog.setMessage("Do you want to open maps, for to see the current location, where was detected this information?")
                    alertDialog.setButton(
                        DialogInterface.BUTTON_POSITIVE,
                        "Yes"
                    ) { dialog, _ ->
                        dialog.dismiss()

                        val latitudeLongitude = "${countries.lat},${countries.lon}"
                        val zoom = 6
                        val actionMap = "api=1&map_action=map"
                        val center = "center=$latitudeLongitude"
                        val z = "zoom=$zoom"
                        val web = "https://www.google.com/maps/@?$actionMap&$center&$z"

                        val geoUriWeb = Uri.parse(web)
                        val intentWeb = Intent(Intent.ACTION_VIEW, geoUriWeb)
                        startActivity(intentWeb)
                    }

                    alertDialog.setButton(
                        DialogInterface.BUTTON_NEGATIVE,
                        "Cancel"
                    ) { dialog, _ ->
                        dialog.dismiss()
                    }
                    alertDialog.show()
                }
            }
        )

        stateConfirmedByCountryViewModel.getStateConfirmedByCountryViewModel(
            country.toString()
        ).observe(viewLifecycleOwner,
            Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                            progressBar.show()

                        }

                        Status.ERROR -> {
                            progressBar.hide()
                        }

                        Status.SUCCESS -> {
                            progressBar.hide()
                            resource.data.let { response ->
                                adapterCountryDetails.addAll(response!!)
                                recyclerView.adapter = adapterCountryDetails
                            }
                        }
                    }
                }
            }
        )
    }
}
