package com.clases.carlosponton.tutorstudend;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by carlosponton on 3/06/18.
 */

public class Datos {
    private static String db = "Estudent";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void edit(Student student){
        databaseReference.child(db).child(student.getId()).setValue(student);
    }

    public static void save(Student student){
        databaseReference.child(db).child(student.getId()).setValue(student);
    }
    public static void delete(Student student){
        databaseReference.child(db).child(student.getId()).removeValue();
    }

}
