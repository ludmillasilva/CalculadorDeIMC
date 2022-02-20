package com.ludmilla.calculadordeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.ludmilla.calculadordeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bt_calcular = binding.btnCalcular
        val mensagem = binding.txtResultado

        bt_calcular.setOnClickListener {
            val editPeso = binding.edtPeso.text.toString()
            val editAltura = binding.edtAltura.text.toString()

            if (editPeso.isEmpty()) {
               mensagem.setText("Informe o seu Peso")
            }else if(editAltura.isEmpty()){
                mensagem.setText("Informe a sua Altura")
            }else{
                calculoDeIMC()
            }

        }
    }
    private fun calculoDeIMC(){

        val pesoID = binding.edtPeso
        val alturaID = binding.edtAltura

        val peso = Integer.parseInt(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        val resultado = binding.txtResultado
        val imc = peso / (altura * altura)

        val msg = when {
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }
        imc.toString()
        resultado.setText("IMC: $imc \n $msg")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.reset -> {
                val limparEdtPeso = binding.edtPeso
                val limparEdtAltura = binding.edtAltura
                val limparMensagem = binding.txtResultado

                limparEdtPeso.setText("")
                limparEdtAltura.setText("")
                limparMensagem.setText("")
            }


        }
        return super.onOptionsItemSelected(item)
    }
}