package com.example.enerjisaapp_007



data class InvoiceResponseData (
    val totalPrice: String? = null,
    val totalPriceCount: String? = null,
    val list: List<ContractAccount>? = null,
    val invoices: List<Invoice>? = null
    )