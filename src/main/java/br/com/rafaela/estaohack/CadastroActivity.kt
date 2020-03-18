package br.com.rafaela.estaohack

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Criando meu Shared Preference
        val minhaPreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)
        //Criando o editor
        val meuEditor = minhaPreferencia.edit()
        //Criando uma lista para o Spinner
        val listaSexo = arrayListOf("selecione o sexo", "Masculino", "Feminino")
        //Criando o meu adapter
        val adapterSexo = ArrayAdapter(
            this@CadastroActivity,
            android.R.layout.simple_spinner_dropdown_item, listaSexo
        )

        spnSexo.adapter = adapterSexo

        btnRegister.setOnClickListener {
            val nome = edtName.text.toString().trim()
//            val sobrenome = edtLastname.text.toString().trim()
            val senha = edtPass.text.toString().trim()
            val email = edtEmail.text.toString().trim().toLowerCase()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(
                    this@CadastroActivity,
                    "Por favor preencha todos os campos",
                    Toast.LENGTH_LONG
                ).show()

            } else if (spnSexo.selectedItem == "Selecione o sexo") {
                Toast.makeText(
                    this@CadastroActivity,
                    "Selecione seu sexo na lista",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                //Gravando as informações dentro do Shared Preferences
                meuEditor.putString("nome", nome).apply()
//                meuEditor.putString("sobrenome", sobrenome).apply()
                meuEditor.putString("senha", senha).apply()
                meuEditor.putString("email", email).apply()
                meuEditor.putString("sexo", spnSexo.selectedItem.toString()).apply()

                //Criando um alert para o shared preference

                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Sucesso")
                    .setMessage("Usuario cadastrado com sucesso")
                    .setPositiveButton("Ok") { _, _ ->

                        onBackPressed()
                    }
                    .create()
                    .show()

            }

        }
    }
}

