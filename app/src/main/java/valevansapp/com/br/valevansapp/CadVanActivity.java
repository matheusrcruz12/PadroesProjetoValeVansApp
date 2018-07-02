package valevansapp.com.br.valevansapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import valevansapp.com.br.valevansapp.modelo.FirebaseDB;
import valevansapp.com.br.valevansapp.modelo.Van;

public class CadVanActivity extends Activity {


    private EditText editPlaca;
    private EditText editNome;
    private EditText editLugares;
    private CheckBox checkArCond;
    private Van van;
    private Button btnCadastraVan;
    int count = 0;
    private DatabaseReference dbVans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_van);

        editLugares = findViewById(R.id.editLugaresId);
        editNome = findViewById(R.id.editNomeEmpresaId);
        editPlaca = findViewById(R.id.editPlacaUpdateId);
        btnCadastraVan = findViewById(R.id.btnCadVanId);
        iniciaBanco();
        btnCadastraVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                van = Van.getInstance();
                count = Integer.parseInt(editLugares.getText().toString());
                van.setLugares(Integer.parseInt(editLugares.getText().toString()));
                van.setNome(editNome.getText().toString());
                van.setPlaca(editPlaca.getText().toString());

                dbVans.child(van.getPlaca()).setValue(van);
                Toast.makeText(getApplicationContext(),"Van de placa: "+van.getPlaca()+" Cadastrada com sucesso!",Toast.LENGTH_SHORT).show();
                limpaCampos();
            }
        });






    }

    private void iniciaBanco(){
        FirebaseDB fb = new FirebaseDB();
        dbVans = fb.iniciaBanco("Van");
    }
    private void limpaCampos(){
        editPlaca.setText("");
        editLugares.setText("");
        editNome.setText("");
    }
}
