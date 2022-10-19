package com.example.helpapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recview, recyclerView;
    myadapter adapter;
    ProgressBar progressBar;


    // Make sure to use the FloatingActionButton for all the FABs
    FloatingActionButton mAddFab, mAddAmbulanceFab;

    // These are taken to make visible and invisible along with FABs
    TextView addAmbulanceActionText;

    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;

    EditText editText;

    ArrayList<model> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        progressBar = findViewById(R.id.progressBarA);

        arrayList = new ArrayList<>();

        mAddFab = findViewById(R.id.add_fab);

        mAddAmbulanceFab = findViewById(R.id.add_ambulance_fab);

        addAmbulanceActionText = findViewById(R.id.add_ambulance_action_text);

        mAddAmbulanceFab.setVisibility(View.GONE);
        addAmbulanceActionText.setVisibility(View.GONE);


        isAllFabsVisible = false;

        mAddFab.setOnClickListener(view -> {
            if (!isAllFabsVisible) {
                mAddAmbulanceFab.show();
                addAmbulanceActionText.setVisibility(View.VISIBLE);


                isAllFabsVisible = true;
            } else {
                mAddAmbulanceFab.hide();
                addAmbulanceActionText.setVisibility(View.GONE);


                isAllFabsVisible = false;
            }
        });

        mAddAmbulanceFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Add new ambulance",
                        Toast.LENGTH_SHORT).show();

                Intent i = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(i);

            }
        });

        recview=(RecyclerView)findViewById(R.id.rv);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Ambulances"), model.class)
                        .build();

        adapter=new myadapter(options);
        recview.setAdapter(adapter);


        editText = (EditText) findViewById(R.id.search_field);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()){
                    search(convert(editable.toString()));
                }else{
                    search("");
                }

            }
        });

    }

    public static void removeBar(ProgressBar progressBar){
        progressBar.setVisibility(View.GONE);
    }

    static String convert(String str)
    {
        // Create a char array of given String
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {

            // If first character of a word is found
            if (i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {

                // If it is in lower-case
                if (ch[i] >= 'a' && ch[i] <= 'z') {

                    // Convert into Upper-case
                    ch[i] = (char)(ch[i] - 'a' + 'A');
                }
            }

            // If apart from first character
            // Any one is in Upper-case
            else if (ch[i] >= 'A' && ch[i] <= 'Z')

                // Convert into Lower-Case
                ch[i] = (char)(ch[i] + 'a' - 'A');
        }

        // Convert the char array to equivalent String
        String st = new String(ch);
        return st;
    }

    private void search(String s) {
        Query query = FirebaseDatabase.getInstance().getReference().child("Ambulances")
                .orderByChild("location").startAt(s)
                .endAt(s + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    arrayList.clear();
                    for(DataSnapshot dss: snapshot.getChildren()){
                        final model model = dss.getValue(model.class);
                        arrayList.add(model);
                    }

                    SearchAdapter searchAdapter = new SearchAdapter(getApplicationContext(), arrayList);
                    recview.setAdapter(searchAdapter);
                    searchAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recview.setAdapter(adapter);
    }

}