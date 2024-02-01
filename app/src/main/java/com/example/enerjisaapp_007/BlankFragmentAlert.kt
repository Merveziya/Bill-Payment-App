package com.example.enerjisaapp_007

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.enerjisaapp_007.databinding.ItemInvoiceBinding

class BlankFragmentAlert : Fragment(R.layout.fragment_blank_alert) {



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blank_alert,container,false)

        val documentNumber = rootView.findViewById<TextView>(R.id.dokumNumarasi)
        val invDocText = rootView.findViewById<TextView>(R.id.faturaDoku)
        val okButton = rootView.findViewById<Button>(R.id.buttonAlertView)
        if (a == 0) {
            documentNumber.text = arguments?.getString("documentNumberFragment") ?: null
            invDocText.text = "Bu faturaya ait döküman numarası ${arguments?.getString("documentNumberFragment") ?: null}’dir. Bu numaraya istinaden işlemlerinizi gerçekleştirebilirsiniz."

        }
        else if (a == 1) {
            documentNumber.text = arguments?.getString("documentNumberFragment") ?: null
            invDocText.text = "Bu fatura ${arguments?.getString("documentNumberFragment") ?: null} tarihine kadar ödenmesi gerekmektedir."

        }
        okButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }

        return rootView
    }

}