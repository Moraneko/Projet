package com.vogella.android.projet.java.activity.Controler;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vogella.android.projet.R;
import com.vogella.android.projet.java.activity.View.MainPage;


public class MainActivityControler {
    private View v;
    public MainActivityControler(View v){
        this.v=v;
    }
    public void click() {
        TextInputEditText name = v.findViewById(R.id.textEdit);
        if( name.getText().toString().length()>0){
            Intent intent = new Intent(v.getContext(), MainPage.class);
            String nameOfUser = name.getText().toString();
            intent.putExtra("userName",nameOfUser);
            v.getContext().startActivity(intent);
        } else {
            Toast.makeText(v.getContext(), "Choisisez un nom", Toast.LENGTH_SHORT).show();
        }
    }
}
