package com.example.kidmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class ToolsList extends AppCompatActivity {

    ListView toolsListView;
    EditText toolSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_list);

        toolsListView = (ListView) findViewById(R.id.toolsTextView);
        toolSearch = findViewById(R.id.toolSearch);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Forceps");
        arrayList.add("Suction Tube");
        arrayList.add("Diathermy");
        arrayList.add("Retractors");
        arrayList.add("Sutures");


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
        toolsListView.setAdapter(arrayAdapter);

        //add listener
        toolsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ToolsList.this, "clicked: " + arrayList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        toolSearch.addTextChangedListener(new TextWatcher() {
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