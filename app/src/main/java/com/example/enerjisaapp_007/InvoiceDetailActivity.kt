package com.example.enerjisaapp_007

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enerjisaapp_007.databinding.ActivityInvoiceDetailBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InvoiceDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInvoiceDetailBinding
    private var invoiceList = arrayListOf<Invoice>()


    private var myInvAdapter = MyNewAdapter(invoiceList) { tem ->
        if (a == 0) {
            val bundle = Bundle()
            bundle.putString("documentNumberFragment", bundleData())
            val fragment = BlankFragmentAlert() // Kullanmak istediğiniz Fragment'ı burada oluşturun
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, fragment)
                .commit()

        } else if (a == 1) {
            val bundle = Bundle()
            bundle.putString("documentNumberFragment", tem.dueDate)
            val fragment = BlankFragmentAlert() // Kullanmak istediğiniz Fragment'ı burada oluşturun
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, fragment)
                .commit()
        }

    }
    /*    fun filteringContNum(numL: String, numR: String): Boolean {
            if (numL == numR) {
                return true
            } else {
                return false
            }
        }*/

    fun bundleData(): String {
        val bundle = intent.extras
        val insControlNum = bundle?.getString("contractNum").toString()
        return insControlNum

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Geri düğmesine basıldığında MainActivity'e dönüş yap
                val intent = Intent(this@InvoiceDetailActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.gradient_yellow_action_bar))

        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var actionBar = getSupportActionBar()

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back_icon)

            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        val quotesApi = RetrofitServiceInstance.getInstance().create(Apiinterface::class.java)
        binding.activityInvoiceRecyclerView.adapter = myInvAdapter
        binding.activityInvoiceRecyclerView.layoutManager =
            LinearLayoutManager(this@InvoiceDetailActivity, LinearLayoutManager.VERTICAL, false)

        val bundle = intent.extras
        var insControlNum: String = ""
        if (bundle != null) {
            insControlNum = bundle.getString("contractNum").toString()
        }


        lifecycleScope.launch {
            val result_inv = quotesApi.getData()

            runOnUiThread {
                if (result_inv.isSuccessful) {
                    val invoiceResponse = result_inv.body()

                    if (invoiceResponse != null) {
                        binding.activityInvoiceWarning1TextView.text = "Seçili sözleşme hesabınıza ait ${invoiceResponse.totalPriceCount.toString()} adet ödenmemiş fatura bulunmaktadır."
                        binding.sumInvoicePrice.text = invoiceResponse.totalPrice.toString()

                        var forInvoiceDetailList = invoiceResponse.invoices
                        var size = forInvoiceDetailList!!.size

                        val invoiceDetailList: ArrayList<Invoice> = ArrayList(size)
                        if (forInvoiceDetailList != null) {

                            for (i in 0 until forInvoiceDetailList.size) {
                                if (forInvoiceDetailList[i].installationNumber == insControlNum) {
                                    invoiceDetailList.add(forInvoiceDetailList[i])

                                }
                            }

                        }
                        if (invoiceDetailList != null) {
                            myInvAdapter.newInvList(invoiceDetailList as ArrayList<Invoice>)
                        }
                    } else {
                        Log.e("InvoiceActivity", "InvoiceResponseModel is null")
                    }
                } else {
                    Log.e(
                        "InvoiceActivity",
                        "API call failed: ${result_inv.code()} - ${result_inv.message()}"
                    )
                }
            }

            if (bundle != null) {
                insControlNum = bundle.getString("contractNum").toString()


                val listSize = bundle.getString("listSize")
                binding.invoiceActivityCompanyNameTextView.text = bundle.getString("companyName")
                binding.invoiceActivityAddressTextView.text = bundle.getString("address")
                binding.invoiceActivityInstallationNumTextView.text = bundle.getString("contractNum")
                binding.invoiceContractAccountNumTextView.text = bundle.getString("anlageNum")


            }
        }


    }
}