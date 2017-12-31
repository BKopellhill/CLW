package com.bkopellhill.gj.comisionlibre;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;


public class AdvanceActivity extends AppCompatActivity {

    // Instancio un objeto del tipo EditText para la entrada de datos
    EditText edtxtInput;
    EditText edtxtInputPorc;

    // Instancio un objeto del tipo EditText para los resultados
    EditText edtxtResul1Adv;
    EditText edtxtResul2Adv;
    EditText edtxtResul3Adv;

    // Instancio un objeto de tipo Radio Group y los dos Radio Buttons
    RadioGroup rdgrpAltoBajo;
    RadioButton rdalto,rdbajo;

    // Si enlazaba las variables de arriba se caia la aplicacion ni bien abria el intent

    // Tengo definidas estas constantes en Main Activity.
    // Indudablemente debo corregir esto, pero aún no se como.
    final double porcomis=0.11;
    final double comis=1.0-porcomis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        // Enlazo el objeto
        rdalto=(RadioButton) findViewById(R.id.rdBtnAlto);
        rdbajo=(RadioButton) findViewById(R.id.rdBtnBajo);

        rdgrpAltoBajo=(RadioGroup) findViewById(R.id.RdGrpAltoBajo);
        rdgrpAltoBajo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                if (input_Ok(false)==true){
                    CalcResulAdv();
                }
            }
        });
    }

    public boolean input_Ok(boolean muestraAdvertencia){
        // Instancio y enlazo las variables del tipo EditText para el ingreso del importe y %
        EditText edtxtNumInput=(EditText) findViewById(R.id.edtxtInputAdv);
        EditText edtxtPorcInput=(EditText) findViewById(R.id.edtxt_Input_Porc);
        // Si al menos un campo campo esta vacio devuelve False.
        // Guardo en strInputNum el contenido de lo ingresado en txt_ingreso
        String strInputNum=edtxtNumInput.getText().toString() ;
        String strInputPorc=edtxtPorcInput.getText().toString() ;
        if ( (strInputNum != null) && (!strInputNum.equals("")) && (strInputPorc != null) && (!strInputPorc.equals("")) ) {
            // Entro por aquí si tdos los campos están completos
            return true;
        } else {
            // Entro por aquí si por lo menos un campo está vacio
            if (muestraAdvertencia==true){
                Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    public void limpiaCamposAdv(View vista){
        limpiaIngresoAdv();
        limpiaResultadosAdv();
    }

    public void limpiaIngresoAdv(){ // Esto era mostrarResultado()
        edtxtInput=(EditText) findViewById(R.id.edtxtInputAdv);
        edtxtInputPorc=(EditText) findViewById(R.id.edtxt_Input_Porc);
        edtxtInput.setText("");
        edtxtInputPorc.setText("");
    }

    public void limpiaResultadosAdv(){
        // Enlazo dentro del metodo
        edtxtResul1Adv=(EditText) findViewById(R.id.edtxt_resul1_adv);
        edtxtResul2Adv=(EditText) findViewById(R.id.edTxt_resul2_adv);
        edtxtResul3Adv=(EditText) findViewById(R.id.edTxt_ResComAdv);
        edtxtResul1Adv.setText("");
        edtxtResul2Adv.setText("");
        edtxtResul3Adv.setText("");
    }

    public void calculaResultadoAdv(View visresul){
        // Si le paso "true" al metodo es porque sí quiero que me indique que debo
        // mandar un toast indicando que se deben completar todos los campos

        // Si me devuelve true es porque todos los campos de entrada estan completos
        if (input_Ok(true)==true){
            CalcResulAdv();
        }
    }

    public void CalcResulAdv(){

        final double resul2,d_res_comis,d_res_n;

        // Instancio y enlazo las variables del tipo EditText para el ingreso del importe y %
        EditText edtxtNumInput=(EditText) findViewById(R.id.edtxtInputAdv);
        EditText edtxtPorcInput=(EditText) findViewById(R.id.edtxt_Input_Porc);

        // Instancio y enlazo las variables del tipo EditText para los resultados
        edtxtResul1Adv=(EditText) findViewById(R.id.edtxt_resul1_adv);
        edtxtResul2Adv=(EditText) findViewById(R.id.edTxt_ResComAdv);
        edtxtResul3Adv=(EditText) findViewById(R.id.edTxt_resul2_adv);

        // Guardo en strInputNum el contenido de lo ingresado en txt_ingreso
        String strInputNum=edtxtNumInput.getText().toString() ;
        String strInputPorc=edtxtPorcInput.getText().toString() ;


        // Paso de String a Double los campos ingresados
        double d_in_num=Double.parseDouble(strInputNum);
        double d_in_porc=Double.parseDouble(strInputPorc);

        if (rdalto.isChecked()==true){
             resul2=d_in_num+(d_in_num*(d_in_porc/100));
        } else {
             resul2=d_in_num-(d_in_num*(d_in_porc/100));
        }

        // Instancio una variable del tipo BigDecimal (clase Math)
        BigDecimal bdr=BigDecimal.valueOf(resul2);
        // Paso a una variable del tipo doble el resultado del redondeo con 2 decimales del resultado
        double rf=bdr.setScale(2, RoundingMode.HALF_UP).doubleValue();
        // Fin del metodo mas recomendable

        // Nuevo: Convierto a String para evitar la concatenación (sugerencia de Android Studio)
        // String srf=String.valueOf(rf);
        // Reemplazo la linea anterior por la siguiente para mejor formateo de decimales
        String srf=String.format(Locale.GERMANY,"%1$,.2f", rf);
        edtxtResul1Adv.setText(srf);


        // Calculo cuanto me queda con la comision aplicada
        d_res_comis=resul2-(resul2*porcomis);
        BigDecimal bdrc=BigDecimal.valueOf(d_res_comis);
        double rfc=bdrc.setScale(2,RoundingMode.HALF_UP).doubleValue();
        // String srfc=String.valueOf(rfc);
        // Reemplazo la linea anterior por la siguiente para mejor formateo de decimales
        String srfc=String.format(Locale.GERMANY,"%1$,.2f",rfc);
        edtxtResul2Adv.setText(srfc);


        // Calculo cuanto me queda neto excluyendo la comision
        d_res_n=resul2/comis;
        BigDecimal bdrn=BigDecimal.valueOf(d_res_n);
        double rfn=bdrn.setScale(2,RoundingMode.HALF_UP).doubleValue();
        // String srn=String.valueOf(rfn);
        // Reemplazo la linea anterior por la siguiente para mejor formateo de decimales
        String srn=String.format(Locale.GERMANY,"%1$,.2f",rfn);
        edtxtResul3Adv.setText(srn);


        // Para ocultar el teclado numerico cuando presiono el boton Calcular
        // Primero instancio que mi metodo de entrada es el teclado
       InputMethodManager miteclado=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        // Luego oculto el teclado de la siguiente forma
       miteclado.hideSoftInputFromWindow(edtxtNumInput.getWindowToken(),0);

    }

    public void llama_actBas(View view){
        // Intent inAdv2Bas = new Intent(view.getContext(),AdvanceActivity.class);


        // Intent inAdv2Bas = new Intent(this,MainActivity.class);
        // startActivity(inAdv2Bas);

        // En lugar de llamar a un intent nuevo (las 2 lineas de arriba comentadas)
        // invoco a la funcion que es llamada cuando se le da "back" en el celular.
        // Esto ahorra bastante uso de memoria.

        onBackPressed();

    }

    // Como es un metodo de una clase hererada se debe sobreescribir
    @Override public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_main, mimenu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem opcion_menu){
        // Obtener el id de esa opción de menu
        int id=opcion_menu.getItemId();
        if(id==R.id.action_exit){
            finish();
        }

        if(id==R.id.action_about){
            AlertDialog.Builder alerta_about=new AlertDialog.Builder(AdvanceActivity.this);
            alerta_about.setTitle(R.string.menu_about_titulo)
                    .setMessage(R.string.menu_about_contenido)
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alerta_about.create();
            alerta_about.show();
        }


        if(id==R.id.ayuda){
            AlertDialog.Builder alerta_ayuda=new AlertDialog.Builder(AdvanceActivity.this);
            alerta_ayuda.setTitle(R.string.menu_help_titulo)
                    .setMessage(R.string.menu_help_contenido)
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alerta_ayuda.create();
            alerta_ayuda.show();
        }

        // Llamo al metodo de la clase padre para que haga su trabajo (devolverá false)
        return super.onOptionsItemSelected(opcion_menu);
    }


}

