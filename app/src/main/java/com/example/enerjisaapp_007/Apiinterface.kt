package com.example.enerjisaapp_007

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.*

interface Apiinterface {
    @GET("bydevelopertr/enerjisa/main/userInvoices.json")
    suspend fun getData(): Response<InvoiceResponseData>
}