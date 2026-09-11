package com.example.configapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.fragment.app.Fragment

class NotificacoesFragment :
    Fragment(R.layout.fragment_notificacoes) {

    interface NotificacoesListener {
        fun onNotificacoesAlteradas(ativo: Boolean)
    }

    private var listener: NotificacoesListener? = null

    private var notificacoesAtivas = false

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is NotificacoesListener) {
            listener = context
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val switchNotificacoes =
            view.findViewById<Switch>(
                R.id.switchNotificacoes
            )

        switchNotificacoes.isChecked =
            notificacoesAtivas

        switchNotificacoes
            .setOnCheckedChangeListener { _, isChecked ->

                notificacoesAtivas = isChecked

                listener
                    ?.onNotificacoesAlteradas(isChecked)
            }
    }

    fun receberEstado(ativo: Boolean) {
        notificacoesAtivas = ativo
    }
}