package com.example.configapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.fragment.app.Fragment

class AparenciaFragment :
    Fragment(R.layout.fragment_aparencia) {

    interface AparenciaListener {
        fun onTemaAlterado(escuro: Boolean)
    }

    private var listener: AparenciaListener? = null

    private var modoEscuro = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is AparenciaListener) {
            listener = context
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val switchTema =
            view.findViewById<Switch>(
                R.id.switchTema
            )

        switchTema.isChecked = modoEscuro

        switchTema
            .setOnCheckedChangeListener { _, isChecked ->

                modoEscuro = isChecked

                listener?.onTemaAlterado(isChecked)
            }
    }

    fun receberTema(escuro: Boolean) {
        modoEscuro = escuro
    }
}