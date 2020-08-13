package ao.covidtracking.romavicdosanjos.data.models

import com.google.gson.annotations.SerializedName

data class SummaryCallback(
    @SerializedName("Global")
    val global: Global = Global(),
    @SerializedName("Countries")
    val countries: MutableList<Country> = mutableListOf(),
    @SerializedName("Date")
    val date: String = ""
)

data class Global(
    @SerializedName("NewConfirmed")
    val newConfirmed: Int = 0,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int = 0,
    @SerializedName("NewDeaths")
    val newDeaths: Int = 0,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int = 0,
    @SerializedName("NewRecovered")
    val newRecovered: Int = 0,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int = 0
)

data class Country(
    @SerializedName("Country")
    val country: String = "",
    @SerializedName("CountryCode")
    val countryCode: String = "",
    @SerializedName("Slug")
    val slug: String = "",
    @SerializedName("NewConfirmed")
    val newConfirmed: Int = 0,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int = 0,
    @SerializedName("NewDeaths")
    val newDeaths: Int = 0,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int = 0,
    @SerializedName("NewRecovered")
    val newRecovered: Int = 0,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int = 0,
    @SerializedName("Date")
    val date: String = ""
)