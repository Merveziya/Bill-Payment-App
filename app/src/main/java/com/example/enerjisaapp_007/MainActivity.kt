package com.example.enerjisaapp_007

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enerjisaapp_007.databinding.ActivityInvoiceDetailBinding
import com.example.enerjisaapp_007.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var invoiceDetailBinding: ActivityInvoiceDetailBinding
    private var myContractAccount = arrayListOf<ContractAccount>()



    private var myAdapter = MyAdapter(myContractAccount) { tem ->
        var contNum = tem.installationNumber.toString()
        val bundle = Bundle()
        val intent = Intent(this@MainActivity, InvoiceDetailActivity::class.java)
        var size = myContractAccount.size



        bundle.putString("contractNum", contNum.toString())
        bundle.putString("address", tem.address.toString())
        bundle.putString("amount", tem.amount.toString())
        bundle.putString("anlageNum", tem.contractAccountNumber.toString())
        bundle.putString("companyName", tem.company.toString())
        bundle.putString("listSize", size.toString())
        bundle.putString("amount1", myContractAccount[0].amount.toString())
        bundle.putString("amount2", myContractAccount[1].amount.toString())



        intent.putExtras(bundle)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_yellow_action_bar))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("FATURA LİSTESİ")


        val quotesApi = RetrofitServiceInstance.getInstance().create(Apiinterface::class.java)
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)


        lifecycleScope.launch {
            val result = quotesApi.getData()

            if (result.isSuccessful) {
                val invoiceResponse = result.body()
                if (invoiceResponse != null) {
                    val invCount = invoiceResponse.totalPriceCount.toString()
                    val invPrice = invoiceResponse.totalPrice.toString()


                    binding.mainActivityNotPayedInvoiceCountTextView.text = "Tüm sözleşme hesaplarınıza ait $invCount adet ödenmemiş fatura bulunmaktadır."
                    binding.mainActivityTotalInvoicePrice.text = invPrice

                    val contractAccounts = invoiceResponse.list

                    if (contractAccounts != null) {
                        myAdapter.newList(contractAccounts as ArrayList<ContractAccount>)
                    }
                } else {
                    Log.e("MainActivity", "InvoiceResponseModel is null")
                }
            } else {
                Log.e("MainActivity", "API call failed: ${result.code()} - ${result.message()}")
            }

        }



    }

}