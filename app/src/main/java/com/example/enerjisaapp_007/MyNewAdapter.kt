package com.example.enerjisaapp_007

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.enerjisaapp_007.databinding.ActivityInvoiceDetailBinding
import com.example.enerjisaapp_007.databinding.ItemInvoiceBinding

class MyNewAdapter(val invoices: ArrayList<Invoice>, val callback: (Invoice) -> Unit):
    RecyclerView.Adapter<MyNewAdapter.InvoicePageHolder>() {

    class InvoicePageHolder(val binding: ItemInvoiceBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoicePageHolder {
        val view = ItemInvoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InvoicePageHolder(view)
    }

    override fun onBindViewHolder(holder: InvoicePageHolder, position: Int) {

        val invoiceDetailList = invoices[position]

//        holder.binding.moneyCurrency.text = invoiceDetailList.currency.toString().ifEmpty { "Hata" }
        holder.binding.textViewInvoiceDueDate.text = invoiceDetailList.dueDate.toString().ifEmpty { "Hata" }
        holder.binding.textViewInvoiceAmount.text = "₺" + invoiceDetailList.amount.toString().ifEmpty { "Hata" }
        holder.binding.invoiceDetailImgButton.setOnClickListener {
            a = 0
            callback.invoke(invoiceDetailList)

        }
        holder.binding.payButton.setOnClickListener {
            a = 1
            callback.invoke(invoiceDetailList)

        }
        holder.binding.payButton2.setOnClickListener {
            a = 1
            callback.invoke(invoiceDetailList)

        }

    }

    override fun getItemCount(): Int {
        return invoices.size
    }


    fun newInvList(mList: ArrayList<Invoice>) {
        invoices.clear()
        invoices.addAll(mList)
        notifyDataSetChanged()
    }
    }