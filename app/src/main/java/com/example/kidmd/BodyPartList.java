package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class BodyPartList extends AppCompatActivity {

    ListView bprview;
    EditText bpSearch;
    String clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodypart_list);

        bprview = (ListView) findViewById(R.id.bprview);
        bpSearch = findViewById(R.id.bpSearch);

        //set up array for ListView
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Appendix");
        arrayList.add("Brain");
        arrayList.add("Gallbladder");
        arrayList.add("Kidney");
        arrayList.add("Liver");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");
        arrayList.add("TBD");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                //Get the current item for ListView
                View view = super.getView(position, convertView, parent);
                if(position %2 == 1){
                    //set background colors
                    view.setBackgroundColor(Color.rgb(173,216,230));
                    view.getBackground().setAlpha(220);
                }
                else{
                    view.setBackgroundColor(Color.rgb(33, 155, 163));
                    view.getBackground().setAlpha(100);

                }
                return view;
            }
        };
        bprview.setAdapter(arrayAdapter);

        //add listener
        bprview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(BodyPartList.this, "clicked: " + arrayList.get(i), Toast.LENGTH_SHORT).show();
                //open body part activity
                switch (arrayList.get(i)) {
                    case "Appendix":
                        startActivity(new Intent(BodyPartList.this, Appendix.class));
                        break;
                    case "Brain":
                        startActivity(new Intent(BodyPartList.this, Brain.class));
                        break;
                    case "Gallbladder":
                        startActivity(new Intent(BodyPartList.this, Gallbladder.class));
                        break;
                    case "Kidney":
                        startActivity(new Intent(BodyPartList.this, Kidney.class));
                        break;
                    case "Liver":
                        startActivity(new Intent(BodyPartList.this, Liver.class));
                        break;
                }

            }
        });

        bpSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}
