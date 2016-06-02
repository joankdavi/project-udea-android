package co.com.seguratec.theintell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    private static EditText username;
    private static EditText password;
    private static Button login_button;
    int attempt_counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton();
    }

    public void LoginButton(){
        username = (EditText)findViewById(R.id.user);
        password = (EditText)findViewById(R.id.password);
        login_button = (Button)findViewById(R.id.button);

        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")){
                            //Toast.makeText(login.this,"Username and password is correct",
                            //        Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("co.com.seguratec.theintell.MainActivity");
                            //startActivity(intent);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            //Toast.makeText(login.this,"Username and password is NOT correct",
                             //       Toast.LENGTH_SHORT).show();
                            attempt_counter--;
                            if(attempt_counter==0)
                                login_button.setEnabled(false);
                        }
                    }
                }
        );
    }
}
