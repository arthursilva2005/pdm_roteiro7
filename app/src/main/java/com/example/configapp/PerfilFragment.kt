package com.example.configapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class PerfilFragment :
    Fragment(R.layout.fragment_perfil) {

    interface PerfilListener {
        fun onNomeSalvo(nome: String)
    }

    private var listener: PerfilListener? = null

    private var nomeAtual = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is PerfilListener) {
            listener = context
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val edtNome =
            view.findViewById<EditText>(R.id.edtNome)

        val btSalvar =
            view.findViewById<Button>(R.id.btSalvar)

        edtNome.setText(nomeAtual)

        btSalvar.setOnClickListener {

            val nome =
                edtNome.text.toString()

            listener?.onNomeSalvo(nome)
        }
    }

    fun receberNome(nome: String) {
        nomeAtual = nome
    }
}