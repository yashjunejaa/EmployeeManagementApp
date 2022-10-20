package com.example.employeemanagement;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

public class DAOItem {

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceDate;
    private DatabaseReference databaseReferenceTransaction;

    public DAOItem() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(Item.class.getSimpleName());
        databaseReferenceDate=firebaseDatabase.getReference("Present");
        databaseReferenceTransaction=firebaseDatabase.getReference(Transaction.class.getSimpleName());

    }

    public Task<Void> add(Item item) {
        String key = databaseReference.push().getKey();
        item.setKey(key);
        return databaseReference.child(key).setValue(item);
    }

    public Query get()
    {
        return databaseReference.orderByKey();
    }

    public Task<Void> addDate(String key, HashMap<String, String> hashMap) {
        return databaseReferenceDate.child(key).setValue(hashMap);
    }

    public Query getDate(String key) {
        return databaseReferenceDate.child(key);
    }

    public Task<Void> addTransaction(String key, Transaction tr) {
        return databaseReferenceTransaction.child(key).child(tr.time+"").setValue(tr);
    }
    public Query getTransaction(String key) {
        return databaseReferenceTransaction.child(key).orderByKey();
    }


}
