package ao.covidtracking.romavicdosanjos.ui.countryDetails.adpaters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ao.covidtracking.romavicdosanjos.R
import ao.covidtracking.romavicdosanjos.data.models.Countries
import ao.covidtracking.romavicdosanjos.utils.dateToString


class CountryDetailsAdapter(
    private var context: Context,
    private var iCountryDetails: ICountryDetails
) : RecyclerView.Adapter<CountryDetailsAdapter.CountryDetailsHolder>() {

    private var countries: MutableList<Countries> = mutableListOf()

    inner class CountryDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val provinceDetail: AppCompatTextView = itemView.findViewById(R.id.provinceDetail)
        private val dateDetail: AppCompatTextView = itemView.findViewById(R.id.dateDetail)
        private val confirmedDetail: AppCompatTextView = itemView.findViewById(R.id.confirmedDetail)
        private val deathsDetail: AppCompatTextView = itemView.findViewById(R.id.deathsDetail)
        private val recoveredDetail: AppCompatTextView = itemView.findViewById(R.id.recoveredDetail)
        private val activeDetail: AppCompatTextView = itemView.findViewById(R.id.activeDetail)

        @SuppressLint("SetTextI18n")
        fun bindCountry(countries: Countries) {
            provinceDetail.text = "Province: ${when
                (countries.province) {
                "" -> {
                    "No data"
                }
                else -> {
                    countries.province
                }
            }}"
            dateDetail.text = "Date: ${dateToString(countries.date)}"
            confirmedDetail.text = "Confirmed: ${countries.confirmed}"
            deathsDetail.text = "Deaths: \n${countries.deaths}"
            recoveredDetail.text = "Recovered: \n${countries.recovered}"
            activeDetail.text = "Active: \n${countries.active}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryDetailsHolder {
        val inflater = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.item_country_detail,
            parent,
            false
        )
        return CountryDetailsHolder(inflater)
    }

    override fun getItemCount(): Int = countries.count()

    override fun onBindViewHolder(
        holder: CountryDetailsHolder,
        position: Int
    ) {
        holder.setIsRecyclable(false)
        holder.bindCountry(countries[position])
        val animation: Animation = AnimationUtils.loadAnimation(
            context,
            android.R.anim.slide_in_left
        )
        holder.itemView.startAnimation(animation)
        holder.itemView.setOnClickListener {
            iCountryDetails.onClick(countries[position])
        }
    }

    fun addAll(item: MutableList<Countries>) {
        countries = item
        notifyDataSetChanged()
    }

    interface ICountryDetails {
        fun onClick(countries: Countries)
    }
}