package com.example.enerjisaapp_007

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.enerjisaapp_007.databinding.ItemLayoutBinding


class MyAdapter(val list: ArrayList<ContractAccount>, val callback: (ContractAccount) -> Unit) :
    RecyclerView.Adapter<MyAdapter.ContractAccountHolder>() {

    class ContractAccountHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractAccountHolder {
        val view = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContractAccountHolder(view)
    }

    override fun onBindViewHolder(holder: ContractAccountHolder, position: Int) {
        // Convert Invoice to ContractAccount and bind it to the view
        val contractAccount = list[position]

        holder.binding.contractNumber.text = contractAccount.contractAccountNumber.orEmpty().ifEmpty { " Empty " }
        holder.binding.address.text = contractAccount.address.orEmpty()
        holder.binding.amount.text = contractAccount.amount.orEmpty()
        holder.binding.anlageNumber.text = contractAccount.installationNumber.orEmpty()
        holder.binding.companyName.text = contractAccount.company.orEmpty()
        holder.binding.buttonAccountPage.setOnClickListener {

            callback.invoke(contractAccount)

        }

//        holder.binding.contractAccount = list[position]
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun newList(myList: ArrayList<ContractAccount>) {
        list.clear()
        list.addAll(myList)
        notifyDataSetChanged()
    }
}
