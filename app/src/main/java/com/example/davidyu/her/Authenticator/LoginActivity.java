package com.example.davidyu.her.Authenticator;

import android.accounts.AccountAuthenticatorActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.davidyu.her.Activities.MainActivity;
import com.example.davidyu.her.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * login screen for app
 */
public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText usernameEditText,passwordEditText;
    TextView registerLink;
    UserLocalStore userLocalStore;
    ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        backgroundImage = (ImageView) findViewById(R.id.loginBackgroundImage);



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

                if (username != "" && password != "") {
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
