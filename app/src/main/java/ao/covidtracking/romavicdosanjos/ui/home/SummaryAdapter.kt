package ao.covidtracking.romavicdosanjos.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ao.covidtracking.romavicdosanjos.R
import ao.covidtracking.romavicdosanjos.model.models.Global
import ao.covidtracking.romavicdosanjos.utils.intToCurrency

class SummaryAdapter : RecyclerView.Adapter<SummaryAdapter.SummaryHolder>() {

    private var dataSummary: Global? = null

    inner class SummaryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtCasesConfirmed: AppCompatTextView =
            itemView.findViewById(R.id.txtCasesConfirmed)
        private val numberConfirmed: AppCompatTextView = itemView.findViewById(R.id.numberConfirmed)
        private val txtCasesDeaths: AppCompatTextView = itemView.findViewById(R.id.txtCasesDeaths)
        private val numberDeaths: AppCompatTextView = itemView.findViewById(R.id.numberDeaths)
        private val txtCasesRecovered: AppCompatTextView =
            itemView.findViewById(R.id.txtCasesRecovered)
        private val numberRecovered: AppCompatTextView = itemView.findViewById(R.id.numberRecovered)

        @SuppressLint("SetTextI18n")
        fun bindSummary(global: Global) {
            txtCasesConfirmed.text = "TotalConfirmed"
            numberConfirmed.text = "${intToCurrency(global.totalConfirmed)} \nPeoples"
            txtCasesDeaths.text = "TotalDeaths"
            numberDeaths.text = "${intToCurrency(global.totalDeaths)} \nPeoples"
            txtCasesRecovered.text = "TotalRecovered"
            numberRecovered.text = "${intToCurrency(global.totalRecovered)} \nPeoples"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryHolder {
        val inflater = LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.item_summary,
            parent,
            false
        )
        return SummaryHolder(inflater)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: SummaryHolder, position: Int) {
        dataSummary?.let {
            holder.bindSummary(it)
        }
    }

    fun addAll(input: Global) {
        dataSummary = input
        notifyDataSetChanged()
    }
}