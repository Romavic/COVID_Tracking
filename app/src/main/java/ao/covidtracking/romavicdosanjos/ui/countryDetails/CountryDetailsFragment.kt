@file:Suppress("DEPRECATION")

package ao.covidtracking.romavicdosanjos.ui.countryDetails

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.covidtracking.romavicdosanjos.R
import ao.covidtracking.romavicdosanjos.model.models.Countries

class CountryDetailsFragment : Fragment() {

    private lateinit var txtCountry: AppCompatTextView
    private lateinit var countryDetailsViewModel: CountryDetailsViewModel
    private lateinit var adapterCountryDetails: CountryDetailsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_country, container, false).apply {
            val country = requireArguments().getString("country")

            countryDetailsViewModel = ViewModelProviders.of(
                this@CountryDetailsFragment,
                CountryDetailsFactory(country!!)
            ).get(CountryDetailsViewModel::class.java)

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

            adapterCountryDetails = CountryDetailsAdapter(
                context,
                object : CountryDetailsAdapter.ICountryDetails {
                    override fun onClick(countries: Countries) {
                        alertDialog = AlertDialog.Builder(context).create()
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

            countryDetailsViewModel.dataCountryDetails.observe(viewLifecycleOwner,
                Observer {
                    when {
                        it.isNotEmpty() -> {
                            progressBar.visibility = View.GONE
                            adapterCountryDetails.addAll(it)
                            recyclerView.adapter = adapterCountryDetails
                        }
                        else -> {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            )
        }
    }
}
