package com.example.kviz;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.AppDataBase;
import db.Rec;

public class dodavanje extends AppCompatActivity {

    EditText nemacki;
    EditText srpski;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);

        srpski = (EditText) findViewById(R.id.srpObr);
        nemacki = (EditText) findViewById(R.id.nemObr);
        btn = (Button) findViewById(R.id.obr);
        nemacki.requestFocus();


        nemacki.setImeOptions(EditorInfo.IME_ACTION_DONE);
        srpski.setImeOptions(EditorInfo.IME_ACTION_DONE);

        nemacki.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    srpski.requestFocus();
                    return true;
                }
                return false;
            }
        });

        srpski.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    saveRec(srpski.getText().toString(), nemacki.getText().toString());
                    Toast.makeText(getApplicationContext(),"DODATO", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRec(srpski.getText().toString(), nemacki.getText().toString());
                Toast.makeText(getApplicationContext(),"DODATO", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void saveRec(String srpski, String nemacki) {
        AppDataBase db  = AppDataBase.getDbInstance(this.getApplicationContext());

        Rec rec = new Rec();
        rec.srpski = srpski;
        rec.nemacki = nemacki;
        db.RecDao().insertAll(rec);

        finish();

    }

}