package valevansapp.com.br.valevansapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.ArrayList;

import valevansapp.com.br.valevansapp.modelo.FirebaseDB;
import valevansapp.com.br.valevansapp.modelo.Van;

public class ListaVansActivity extends Activity {

    private List<Van> listVans = new ArrayList<>();
    private ArrayAdapter<Van> vanArrayAdapter;
    private DatabaseReference vanDataBase;
    private ListView listaVans;
    private Van vanSelcionada;
    private EditText editPlaca, editNome, editLugares;
    private Button btnAtualizar, btnExcluir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vans);
        iniciaBanco();
        btnAtualizar = findViewById(R.id.btnAtualizarId);
        btnExcluir =findViewById(R.id.btnExcluirId);
        editPlaca = findViewById(R.id.editPlacaUpdateId);
        editNome = findViewById(R.id.editNomeEmpId);
        editLugares = findViewById(R.id.editLugaresId);

    //editPlaca.setText("AAAAAAAAAAAAAAA");


        listaVans = findViewById(R.id.listaVansId);
        listaVans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vanSelcionada = (Van)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),""+vanSelcionada.getPlaca(),Toast.LENGTH_SHORT).show();
                editPlaca.setText(vanSelcionada.getPlaca());
                editNome.setText(vanSelcionada.getNome());
                editLugares.setText(""+vanSelcionada.getLugares());
            }
        });





        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Van vanUpdate = Van.getInstance();
                vanUpdate.setPlaca(editPlaca.getText().toString().trim());
                vanUpdate.setNome(editNome.getText().toString().trim());
                vanUpdate.setLugares(Integer.parseInt(editLugares.getText().toString()));

                if (vanUpdate.getPlaca().equals(vanSelcionada.getPlaca())){

                }else vanDataBase.child(vanSelcionada.getPlaca()).removeValue();

                vanDataBase.child(vanUpdate.getPlaca()).setValue(vanUpdate);
                limpaCampos();
            }


        });
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vanDataBase.child(vanSelcionada.getPlaca()).removeValue();
                limpaCampos();
            }
        });
        eventoDataBase();
    }

    private void eventoDataBase() {
        vanDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listVans.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Van v = objSnapshot.getValue(Van.class);
                    listVans.add(v);
                }
                vanArrayAdapter = new ArrayAdapter<>(ListaVansActivity.this,android.R.layout.simple_list_item_1,listVans);
                listaVans.setAdapter(vanArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void limpaCampos() {
        editLugares.setText("N° Lugares");
        editNome.setText("Nome Empresa");
        editPlaca.setText("Placa");
    }

   // Toast.makeText(getApplicationContext(),"Van de placa: "+van.getPlaca()+" Cadastrada com sucesso!",Toast.LENGTH_SHORT).show();

    private void iniciaBanco(){
        FirebaseDB fb = new FirebaseDB();
        vanDataBase = fb.iniciaBanco("Van");
    }
}
