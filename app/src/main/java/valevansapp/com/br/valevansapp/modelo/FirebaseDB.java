package valevansapp.com.br.valevansapp.modelo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDB {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public FirebaseDB() {

    }

    public DatabaseReference iniciaBanco(String no){

        DatabaseReference databaseReference = firebaseDatabase.getReference().child(no);
       // firebaseDatabase.setPersistenceEnabled(true);
        return databaseReference;
    }

}
