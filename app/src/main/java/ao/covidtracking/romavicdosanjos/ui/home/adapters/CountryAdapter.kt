package ao.covidtracking.romavicdosanjos.ui.home.adapters

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
import ao.covidtracking.romavicdosanjos.data.models.Country

class CountryAdapter(
    private var context: Context,
    private var iCountry: ICountry
) : RecyclerView.Adapter<CountryAdapter.SummaryHolder>() {

    private var countries: MutableList<Country> = mutableListOf()
    private var arrayList: MutableList<Country> = mutableListOf()

    inner class SummaryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtCountries: AppCompatTextView = itemView.findViewById(R.id.txtCountries)

        @SuppressLint("SetTextI18n")
        fun bindSummary(country: Country) {
            txtCountries.text = country.country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryHolder {
        val inflater = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.item_countries,
            parent,
            false
        )
        return SummaryHolder(inflater)
    }

    override fun getItemCount(): Int = countries.count()

    override fun onBindViewHolder(holder: SummaryHolder, position: Int) {
        holder.bindSummary(countries[position])
        val animation: Animation = AnimationUtils.loadAnimation(
            context,
            android.R.anim.slide_in_left
        )
        holder.itemView.startAnimation(animation)
        holder.itemView.setOnClickListener {
            iCountry.onClick(countries[position])
        }
    }

    fun addAll(input: MutableList<Country>) {
        countries = input
        notifyDataSetChanged()
    }

    interface ICountry {
        fun onClick(countries: Country)
    }
}