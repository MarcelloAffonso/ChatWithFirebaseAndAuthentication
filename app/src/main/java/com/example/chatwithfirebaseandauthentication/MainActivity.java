package com.example.chatwithfirebaseandauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText loginEditText;
    private EditText senhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginEditText = findViewById(R.id.loginEditText);
        senhaEditText = findViewById(R.id.senhaEditText);

        // teste hard coded
        //loginEditText.setText("teste@teste.com");
        //senhaEditText.setText("123456");

        // Cria instância do serviço de autorização do Firebase
        mAuth = FirebaseAuth.getInstance();
    }

    // Método responsável por garantir a navegabilidade de uma tela para outra
    public void irParaCadastro(View view) {
        startActivity(new Intent(this, NovoUsuarioActivity.class));
    }

    // Método responsável por validar o login do usuário e caso esteja ok, redirecionar para o chat
    public void fazerLogin(View view) {
        String login = loginEditText.getEditableText().toString();
        String senha = senhaEditText.getEditableText().toString();

        // Utiliza o serviço de autorização criado anteriormente para possibilitar o login
        mAuth.signInWithEmailAndPassword(login,
                senha).addOnSuccessListener((result) -> {
                    startActivity(new Intent(this,  ChatActivity.class)); // caso consiga fazer login redireciona para o chat
        }).addOnFailureListener((exception) -> { // Coloca o listener para o caso de dar erro
            exception.printStackTrace();
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        });

    }

}