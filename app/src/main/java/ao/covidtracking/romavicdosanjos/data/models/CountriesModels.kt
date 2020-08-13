package ao.covidtracking.romavicdosanjos.data.models

import com.google.gson.annotations.SerializedName

class CountriesCallback : ArrayList<Countries>()

data class Countries(
    @SerializedName("Country")
    val country: String = "",
    @SerializedName("CountryCode")
    val countryCode: String = "",
    @SerializedName("Province")
    var province: String = "",
    @SerializedName("City")
    val city: String = "",
    @SerializedName("CityCode")
    val cityCode: String = "",
    @SerializedName("Lat")
    val lat: String = "",
    @SerializedName("Lon")
    val lon: String = "",
    @SerializedName("Confirmed")
    val confirmed: Int = 0,
    @SerializedName("Deaths")
    val deaths: Int = 0,
    @SerializedName("Recovered")
    val recovered: Int = 0,
    @SerializedName("Active")
    val active: Int = 0,
    @SerializedName("Date")
    val date: String = ""
)