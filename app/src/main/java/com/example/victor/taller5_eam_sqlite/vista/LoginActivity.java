package com.example.victor.taller5_eam_sqlite.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.victor.taller5_eam_sqlite.R;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
TextView TitUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TitUsuario = (TextView) findViewById(R.id.titUsuario);
    }
}
