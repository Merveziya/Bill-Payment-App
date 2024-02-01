package com.example.enerjisaapp_007

data class Invoice(
    val legacyContractAccountNumber: String? = null,
    val installationNumber: String? = null,
    val documentNumber: String? = null,
    val amount: String? = null,
    val currency: String? = null,
    val dueDate: String? = null
)
