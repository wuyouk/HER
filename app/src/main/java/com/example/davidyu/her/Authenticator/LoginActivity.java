package com.example.davidyu.her.Authenticator;

import android.accounts.AccountAuthenticatorActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.davidyu.her.Activities.MainActivity;
import com.example.davidyu.her.R;

/**
 * login screen for app
 */
public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText usernameEditText,passwordEditText;
    TextView registerLink;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        userLocalStore = new UserLocalStore(this);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServerRequest serverRequest = new ServerRequest(v.getContext());

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username != "" && password != ""){
                    serverRequest.getUserFromDatabase(username, password);
                }



            }
        });

        registerLink = (TextView) findViewById(R.id.registerTextView);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), RegisterActivity.class));
                finish();
            }
        });
    }

}
