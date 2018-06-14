package com.chat.vaibhav.slambook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String ServerURL = "http://httpvaibhavmaliml.000webhostapp.com/insert.php" ;
    EditText name, email, hobbies, best_friend, contact, crush;
    public static final String ROOT_URL = "http://httpvaibhavmaliml.000webhostapp.com/insert.php";
    Button buttonRegister;
    String TempName, TempEmail,TempHobies,TempFriend,TempContact,TempCrush ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        hobbies = findViewById(R.id.hobby);
        best_friend = findViewById(R.id.bf);
        contact = findViewById(R.id.contact);
        crush = findViewById(R.id.crush);
        buttonRegister = findViewById(R.id.button);
        //Adding listener to button
        buttonRegister.setOnClickListener(this);
    }

    private void insertUser(){
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface

        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuser of our interface
        api.insertUser(

                //Passing the values by getting it from editTexts
                name.getText().toString(),
                email.getText().toString(),
                hobbies.getText().toString(),
                best_friend.getText().toString(),
                contact.getText().toString(),
                crush.getText().toString(),
                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(MainActivity.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    //Overriding onclick method
    @Override
    public void onClick(View v) {
        //Calling insertUser on button click
        insertUser();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}