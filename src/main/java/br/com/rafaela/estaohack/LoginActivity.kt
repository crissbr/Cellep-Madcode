package br.com.rafaela.estaohack

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Criando a ação do Clique do Botão: Entrar

        btnEntrar.setOnClickListener {

            val usuario = edtNome.text.toString().trim()
            val senha = edtSenha.text.toString().trim()

            val minhaPreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)
            val nome = minhaPreferencia.getString("nome","Erro no Shared Preference")
            val password = minhaPreferencia.getString("senha", "Erro no Shared Preference")

            //Condição para verificar se o Usuário e a Senha estão Corretos

            if (usuario.isEmpty()){
                //Usuário Vazio

                Toast.makeText(this@LoginActivity,"Usuário Vazio",Toast.LENGTH_LONG).show()

            }else if(senha.isEmpty()){
                //Verificando se a senha está Vazia
                Toast.makeText(this@LoginActivity,"Senha Vazia",Toast.LENGTH_LONG).show()


            }else if(usuario == nome){
                if (senha == password){

                    //Criando A Intent.
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))

                }else{

                    //Criando um Alert

                    AlertDialog.Builder(this@LoginActivity)
                        .setTitle("Erro de Autenticação")
                        .setMessage("Senha Incorreto")
                        .setPositiveButton("ok"){_,_ ->

                        }

                        .create()
                        .show()
                }
            }else{
                AlertDialog.Builder(this@LoginActivity)
                    .setTitle("Erro de Autenticação")
                    .setMessage("Usuário Incorreto")
                    .setPositiveButton("ok"){_,_ ->

                    }

                    .create()
                    .show()


            }

        }
        btnCadastrar.setOnClickListener {
            startActivity(Intent(this@LoginActivity, CadastroActivity::class.java))
        }
    }
}
