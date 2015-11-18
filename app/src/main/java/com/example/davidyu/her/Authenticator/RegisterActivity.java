package com.example.davidyu.her.Authenticator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.davidyu.her.R;

/**
 * Created by user on 11/13/2015.
 */
public class RegisterActivity extends AppCompatActivity {

    Button registerButton;
    EditText nameEditText, usernameEditText, passwordEditText;

    ServerRequest serverRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        serverRequest = new ServerRequest(this);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (name!="" && username!="" && password!=""){
                    User newUser = new User();
                    newUser.name = name;
                    newUser.username = username;
                    newUser.password = password;

                    serverRequest.registerUser(newUser);

                }


            }
        });
    }
}
