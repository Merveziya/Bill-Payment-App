package com.example.enerjisaapp_007
import com.google.gson.annotations.SerializedName

data class InObject (
    @SerializedName("totalPrice") val totalP: String? = null,
    @SerializedName("totalPriceCount") val totalPrCount: String? = null,
    @SerializedName("list") val list: List<ListData>? = null,
    @SerializedName("invoices") val invList: List<Invoices>? = null


)
data class Invoices (
    @SerializedName("legacyContractAccountNumber") val legacyNum: String? = null,
    @SerializedName("installationNumber") val insNum: String? = null,
    @SerializedName("documentNumber") val docNum: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("currency") val currency: String? = null,
    @SerializedName("dueDate") val dueDate: String? = null
)

data class ListData (
    @SerializedName("company") val company: String? = null,
    @SerializedName("installationNumber") val instNum: String? = null,
    @SerializedName("contractAccountNumber") val contractNum: String? = null,
    @SerializedName("amount") val amount: String? = null,
    @SerializedName("address") val address: String? = null
)
