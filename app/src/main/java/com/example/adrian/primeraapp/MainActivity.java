package com.example.adrian.primeraapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.math.*;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public double ingreso, resultado;
    final double porcomis=0.11;
    final double comis=1.0-porcomis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado=0;
    }

    public void calculaResultado(View visresul){
        CalcResul();
    }

    public void limpiaCampos(View vista){

        limpiaIngreso();
        limpiaResultado();

        // Instancio un objeto de la clase TextView para luego setearle la propiedad Invisible
        // Por el momento dejo comentadas ambas lineas (funcionan ok!)
        // TextView botonLimpia=(TextView) findViewById(R.id.btn_limpiar);
        // botonLimpia.setVisibility(View.INVISIBLE);
    }

    public void limpiaIngreso(){ // Esto era mostrarResultado()
        // Creamos una instancia perteneciente a la clase TextView
        // El objeto textoResultado es de tipo TextView y recibe la que encuentra (...)
        // en la vista que busca segun su Id
        TextView textoIngreso=(TextView) findViewById(R.id.edtxtInput);
        textoIngreso.setText("");
    }

    public void limpiaResultado(){ // Esto era mostrarResultado()
        TextView textoResultado=(TextView) findViewById(R.id.edtxt_resul);
        textoResultado.setText("");

    }

    public void CalcResul(){ // Esto era mostrarResultado()

        // Instancio una variable del tipo EditText
        EditText numero_ingresado=(EditText) findViewById(R.id.edtxtInput);

        // Instancio una variable del tipo TextView
        TextView textoIngreso=(TextView) findViewById(R.id.edtxt_resul);

        // Guardo en MResult el contenido de lo ingresado en txt_ingreso
        String MResult=numero_ingresado.getText().toString() ;

        if ( (MResult != null) && (!MResult.equals("")) ) {
            // Realizo la operación Matemática
            ingreso=Double.parseDouble(MResult);
            resultado=ingreso/comis;

            // Comienzo del metodo mas recomendable
            // instancio una variable del tipo BigDecimal (clase Math)
            BigDecimal bdr=BigDecimal.valueOf(resultado);
            // paso a una variable del tipo doble el resultado del redondeo con 2 decimales del resultado
            double rf=bdr.setScale(2,RoundingMode.HALF_UP).doubleValue();
            // Fin del metodo mas recomendable

            // Nuevo: Convierto a String para evitar la concatenación (sugerencia de Android Studio)
            // Antes usaba la siguiente linea, funcionaba bien pero en numeros grandes me mostraba "E" a la x.
            // String srf=String.valueOf(rf);
            // La reemplacé por la siguiente linea y me funcionó bien.
            // Tuve que forzarle el "Locale.GERMANY" para que me muestre el formato xxx.xxx,xx
            String srf=String.format(Locale.GERMANY,"%1$,.2f", rf);
            textoIngreso.setText(srf);
        } else {
            // textoIngreso.setText("Debes ingresar un numero para calcular su resultado");
            Toast.makeText(this, "Debes ingresar un número para calcular su resultado", Toast.LENGTH_SHORT).show();
        }

        // Para ocultar el teclado numerico cuando presiono el boton Calcular
        // Primero instancio que mi metodo de entrada es el teclado
        InputMethodManager miteclado=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        // Luego oculto el teclado de la siguiente forma
        miteclado.hideSoftInputFromWindow(numero_ingresado.getWindowToken(),0);

    }

    public void llama_actAdv(View view){
        // Intent inBas2Adv = new Intent(view.getContext(),AdvanceActivity.class);
        Intent inBas2Adv = new Intent(this,AdvanceActivity.class);
        startActivity(inBas2Adv);
    }

}