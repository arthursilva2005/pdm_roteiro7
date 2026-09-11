package com.example.configapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(),
    MenuPrincipalFragment.MenuListener,
    PerfilFragment.PerfilListener,
    NotificacoesFragment.NotificacoesListener,
    AparenciaFragment.AparenciaListener {

    private lateinit var txtStatus: TextView

    private var nomeUsuario = ""
    private var opcaoGeral = false
    private var notificacoes = false
    private var modoEscuro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        txtStatus = findViewById(R.id.txtStatus)

        val btMenu = findViewById<Button>(R.id.btMenu)
        val btPerfil = findViewById<Button>(R.id.btPerfil)
        val btNotificacoes = findViewById<Button>(R.id.btNotificacoes)
        val btAparencia = findViewById<Button>(R.id.btAparencia)

        if (savedInstanceState == null) {
            abrirMenu()
        }

        btMenu.setOnClickListener {
            abrirMenu()
        }

        btPerfil.setOnClickListener {

            val fragment = PerfilFragment()

            fragment.receberNome(nomeUsuario)

            mudarFragment(fragment)
        }

        btNotificacoes.setOnClickListener {

            val fragment = NotificacoesFragment()

            fragment.receberEstado(notificacoes)

            mudarFragment(fragment)
        }

        btAparencia.setOnClickListener {

            val fragment = AparenciaFragment()

            fragment.receberTema(modoEscuro)

            mudarFragment(fragment)
        }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {

                    val fragmentAtual =
                        supportFragmentManager.findFragmentById(
                            R.id.frame_container
                        )

                    if (fragmentAtual !is MenuPrincipalFragment) {
                        abrirMenu()
                    } else {
                        finish()
                    }
                }
            }
        )
    }

    private fun mudarFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.frame_container,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }

    private fun abrirMenu() {

        supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )

        val fragment = MenuPrincipalFragment()

        fragment.receberEstado(opcaoGeral)

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.frame_container,
                fragment
            )
            .commit()
    }

    override fun onOpcaoMenuAlterada(ativo: Boolean) {

        opcaoGeral = ativo

        if (ativo) {
            txtStatus.text = "Recursos gerais: ATIVADOS"
        } else {
            txtStatus.text = "Recursos gerais: DESATIVADOS"
        }
    }

    override fun onNomeSalvo(nome: String) {

        nomeUsuario = nome

        txtStatus.text = "Nome salvo: $nome"
    }

    override fun onNotificacoesAlteradas(ativo: Boolean) {

        notificacoes = ativo

        if (ativo) {
            txtStatus.text = "Notificações: ATIVADAS"
        } else {
            txtStatus.text = "Notificações: DESATIVADAS"
        }
    }

    override fun onTemaAlterado(escuro: Boolean) {

        modoEscuro = escuro

        if (escuro) {
            txtStatus.text = "Modo escuro: ATIVADO"
        } else {
            txtStatus.text = "Modo escuro: DESATIVADO"
        }
    }
}