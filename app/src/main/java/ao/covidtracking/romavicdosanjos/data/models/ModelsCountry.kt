package ao.covidtracking.romavicdosanjos.data.models


import com.google.gson.annotations.SerializedName

class ModelsCountry : ArrayList<ModelsCountryItem>()

data class ModelsCountryItem(
    @SerializedName("Country")
    var country: String = "",
    @SerializedName("Slug")
    var slug: String = "",
    @SerializedName("ISO2")
    var iSO2: String = ""
)