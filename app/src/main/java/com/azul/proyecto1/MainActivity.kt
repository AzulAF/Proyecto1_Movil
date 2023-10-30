package com.azul.proyecto1

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.azul.proyecto1.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    //Funciones matemáticas
    fun chicharronera(numeroA: Int, numeroB: Int, numeroC: Int): Float{
        var op1 = (0+(numeroB*numeroB))-(4*numeroA*numeroC)
        //var d = sqrt(op1.toDouble())
        //var root1 = (0-numeroB+d)/(2*numeroA)
        //var root2 = (0-numeroB-d)/(2*numeroA)
        //Solo para probar
        return op1.toFloat()
    }
    fun discriminante(discrim: Int): Boolean{
        if(discrim<0){
            return false
        }
        return true
    }
    //Validaciones
    fun validaCamposA(): Boolean = binding.etVariableA.text.isNotEmpty()
    fun validaCamposB(): Boolean = binding.etVariableB.text.isNotEmpty()
    fun validaCamposC(): Boolean = binding.etVariableC.text.isNotEmpty()
    fun validaCamposT(): Boolean = binding.etVariableT.text.isNotEmpty()
    fun validaCamposM1(): Boolean = binding.etVariableM1.text.isNotEmpty()
    fun validaCamposM2(): Boolean = binding.etVariableM2.text.isNotEmpty()
    fun validaCamposVF(): Boolean = binding.etVariableVF.text.isNotEmpty()
    fun validaCamposVI(): Boolean = binding.etVariableVI.text.isNotEmpty()
    fun validaCamposR(): Boolean = binding.etVariableR.text.isNotEmpty()

    fun fuerzaGravitacional(masa1: Float, masa2: Float, radio: Float): Float{
        var op1 = masa1 * masa2
        var op2 = radio*radio
        var op3 = (op1/op2)
        var resultado = (0.00000000006774*op3)/0.000000001
        return resultado.toFloat()
    }

    fun aceleracion(velocidadInicial: Float, velocidadFinal: Float, tiempo: Float): Float{
        var op1 = velocidadFinal - velocidadInicial
        var op2 = op1/tiempo
        return op2
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Se coloca el viewbinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Se coloca el spinner
        //Spinner spOperaciones.onItemSelectedListener = object : OnItemSelectedListener

        val spinner : Spinner = findViewById(R.id.spOperaciones)
        var items = resources.getStringArray(R.array.Operaciones)

        ArrayAdapter.createFromResource(
            this,
            R.array.Operaciones,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Se especifica el layout  para el tipo de  dropdown del spinner
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Se aplica el adaptador al spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                //val selectedItem = items[position]
                val selectedItem = items[0]
                //Toast.makeText(this@MainActivity, "$selectedItem",Toast.LENGTH_LONG).show()
                //Ecuación
                if (items[position]==items[0]){
                    binding.etVariableA.visibility = View.VISIBLE
                    binding.etVariableB.visibility = View.VISIBLE
                    binding.etVariableC.visibility = View.VISIBLE
                    binding.tvFormula.visibility= View.VISIBLE
                    binding.btnCalculate.visibility = View.VISIBLE
                    binding.tvResult.visibility = View.VISIBLE
                    //binding.tvInstructions.visibility = View.VISIBLE
                }else{
                    binding.etVariableA.visibility = View.INVISIBLE
                    binding.etVariableB.visibility = View.INVISIBLE
                    binding.etVariableC.visibility = View.INVISIBLE
                    binding.tvFormula.visibility= View.INVISIBLE
                    binding.btnCalculate.visibility = View.INVISIBLE
                    binding.tvResult.visibility = View.INVISIBLE
                    //binding.tvInstructions.visibility = View.INVISIBLE
                }
                //Gravedad
                if (items[position]==items[1]){
                    binding.etVariableR.visibility = View.VISIBLE
                    binding.etVariableM1.visibility = View.VISIBLE
                    binding.etVariableM2.visibility = View.VISIBLE
                    binding.tvFormula2.visibility= View.VISIBLE
                    binding.btnCalculate2.visibility = View.VISIBLE
                    binding.tvResult2.visibility = View.VISIBLE
                    //binding.tvInstructions.visibility = View.VISIBLE
                }else{
                    binding.etVariableR.visibility = View.INVISIBLE
                    binding.etVariableM1.visibility = View.INVISIBLE
                    binding.etVariableM2.visibility = View.INVISIBLE
                    binding.tvFormula2.visibility= View.INVISIBLE
                    binding.btnCalculate2.visibility = View.INVISIBLE
                    binding.tvResult2.visibility = View.INVISIBLE
                    //binding.tvInstructions.visibility = View.INVISIBLE
                }
                //Aceleración
                if (items[position]==items[2]){
                    binding.etVariableVF.visibility = View.VISIBLE
                    binding.etVariableVI.visibility = View.VISIBLE
                    binding.etVariableT.visibility = View.VISIBLE
                    binding.tvFormula3.visibility= View.VISIBLE
                    binding.btnCalculate3.visibility = View.VISIBLE
                    binding.tvResult3.visibility = View.VISIBLE
                    //binding.tvInstructions.visibility = View.VISIBLE
                }else{
                    binding.etVariableVF.visibility = View.INVISIBLE
                    binding.etVariableVI.visibility = View.INVISIBLE
                    binding.etVariableT.visibility = View.INVISIBLE
                    binding.tvFormula3.visibility= View.INVISIBLE
                    binding.btnCalculate3.visibility = View.INVISIBLE
                    binding.tvResult3.visibility = View.INVISIBLE
                    //binding.tvInstructions.visibility = View.INVISIBLE
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //Validación de los botones de la ecuacion de segundo grado. NO JUNTAR en un mismo binding
        binding.etVariableA.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate.isEnabled = validaCamposA() && validaCamposB() && validaCamposC()
            }
        })

        binding.etVariableB.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate.isEnabled = validaCamposA() && validaCamposB() && validaCamposC()
            }
        })

        binding.etVariableC.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate.isEnabled = validaCamposA() && validaCamposB() && validaCamposC()
            }
        })
        ///HASTA AQUI BOTON DE VARIABLES


        //Validar botones de la fuerza gravitacional
        binding.etVariableR.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate2.isEnabled = validaCamposR() && validaCamposM1() && validaCamposM2()
            }
        })
        binding.etVariableM1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate2.isEnabled = validaCamposR() && validaCamposM1() && validaCamposM2()
            }
        })
        binding.etVariableM2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate2.isEnabled = validaCamposR() && validaCamposM1() && validaCamposM2()
            }
        })
        //HASTA AQUI BOTONES DE GRAVEDAD

        //Validar botones de la aceleracion
        binding.etVariableVF.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate3.isEnabled = validaCamposT() && validaCamposVF() && validaCamposVI()
            }
        })
        binding.etVariableVI.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate3.isEnabled = validaCamposT() && validaCamposVF() && validaCamposVI()
            }
        })
        binding.etVariableT.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCalculate3.isEnabled = validaCamposT() && validaCamposVF() && validaCamposVI()
            }
        })
        //HASTA AQUI BOTONES DE LA ACELERACION



        //Calculo de ecuacion de segundo grado

        binding.btnCalculate.setOnClickListener {
            if(binding.etVariableC.text.isNotEmpty()&&binding.etVariableA.text.isNotEmpty()&&binding.etVariableB.text.isNotEmpty()){
                var var_a = binding.etVariableA.text.toString().toInt()
                var var_b = binding.etVariableB.text.toString().toInt()
                var var_c = binding.etVariableC.text.toString().toInt()
                var d = chicharronera(var_a,var_b,var_c)
                if(d<0){
                    Toast.makeText(this, getString(R.string.error_variables), Toast.LENGTH_LONG).show()
                }else {
                    var resultado = sqrt(d.toDouble())
                    var root1 = (0 - var_b + resultado) / (2 * var_a)
                    var root2 = (0 - var_b - resultado) / (2 * var_a)
                    binding.tvResult.text =
                        getString(R.string.resultados_chicharronera, root1, root2)
                }
                //Toast.makeText(this, "Raiz 1 $root1 y raiz 2 $root2", Toast.LENGTH_LONG).show()
            }

        }

            //Calculo de la fuerza gravitacional
            binding.btnCalculate2.setOnClickListener {
                if (binding.etVariableM1.text.isNotEmpty() && binding.etVariableM2.text.isNotEmpty() && binding.etVariableR.text.isNotEmpty()) {
                    var m1 = binding.etVariableM1.text.toString().toFloat()
                    var m2 = binding.etVariableM2.text.toString().toFloat()
                    var r = binding.etVariableR.text.toString().toFloat()
                    var resultado = fuerzaGravitacional(m1, m2, r)
                    binding.tvResult2.text = getString(R.string.resultados_fuerza, resultado)
                    //binding.tvResult2.text = getString(R.string.Resultado)

                }
            }
                //Calculo de la aceleracion
                binding.btnCalculate3.setOnClickListener {
                    if (binding.etVariableT.text.isNotEmpty() && binding.etVariableVF.text.isNotEmpty() && binding.etVariableVI.text.isNotEmpty()) {
                        var t = binding.etVariableT.text.toString().toFloat()
                        var vf = binding.etVariableVF.text.toString().toFloat()
                        var vi = binding.etVariableVI.text.toString().toFloat()
                        var resultado = aceleracion(vi, vf, t)
                        binding.tvResult3.text = getString(R.string.resultados_aceleracion, resultado)
                    }

                }





    }
}