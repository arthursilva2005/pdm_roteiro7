package com.example.configapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.fragment.app.Fragment

class MenuPrincipalFragment :
    Fragment(R.layout.fragment_menu_principal) {

    interface MenuListener {
        fun onOpcaoMenuAlterada(ativo: Boolean)
    }

    private var listener: MenuListener? = null

    private var estadoAtual = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MenuListener) {
            listener = context
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val switchGeral =
            view.findViewById<Switch>(R.id.switchGeral)

        switchGeral.isChecked = estadoAtual

        switchGeral.setOnCheckedChangeListener { _, isChecked ->

            estadoAtual = isChecked

            listener?.onOpcaoMenuAlterada(isChecked)
        }
    }

    fun receberEstado(ativo: Boolean) {
        estadoAtual = ativo
    }
}