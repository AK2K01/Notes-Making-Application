package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Collection;
import java.util.HashSet;

public class NoteEditor extends AppCompatActivity {
    int index;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        EditText editText=(EditText)findViewById(R.id.editText);
        sharedPreferences=this.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
        Intent intent=getIntent();
        index=intent.getIntExtra("noteId",-1);
        if(index!=-1)
        {
            editText.setText(MainActivity.notes.get(index));
        }
        else
        {
            MainActivity.notes.add("");
            index=MainActivity.notes.size()-1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(index,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                HashSet<String> set=new HashSet<String>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}