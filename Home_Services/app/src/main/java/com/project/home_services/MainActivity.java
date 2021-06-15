package com.project.home_services;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView forgotpass;
    private Button loginbtn;
    private Button signupbtn;
    private Button googlebtn;

    private ProgressBar LoginProgress;
    private TextView TXTLemail;
    private TextView TXTLpassword;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1234;

//    @Override
//    protected void onStart() {        // this method will automatically login if the user is already signed in using google
//        super.onStart();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user !=null){
//            Intent intent = new Intent(getApplicationContext(), HomePage.class  );
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);   // forces to remove dark mode

        //----disable titlebar code
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        getWindow().setNavigationBarColor(getResources().getColor(R.color.ColorAccent));   // changes the navigation bar color

        setContentView(R.layout.activity_main);

        signupbtn = findViewById(R.id.BTNsignup);
        signupbtn.setOnClickListener(this);

        loginbtn = findViewById(R.id.BTNlogin);
        loginbtn.setOnClickListener(this);

        googlebtn = findViewById(R.id.BTNgoogle);
        googlebtn.setOnClickListener(this);

        forgotpass = findViewById(R.id.TVBTNforgot);
        forgotpass.setOnClickListener(this);


        TXTLemail = findViewById(R.id.TXTemailnum);
        TXTLpassword = findViewById(R.id.TXTpassword);
        LoginProgress = findViewById(R.id.LoginProgress);

        createRequest();
        mAuth = FirebaseAuth.getInstance();
    }

    private void createRequest() {
        //COnfigure Google signIN
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);              //Build a GOogleSignINClient
    }
    private void signIn() {                                                     // THis method will be called when user click "google SignIN"
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();          //Will request an intent from system ,to choose from all accounts.
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override         // This method will check the results provided by user (which account)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Signin failed ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), HomePage.class));
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "SignIn Failed", Toast.LENGTH_LONG).show();
//                            Snackbar.make(findViewById(R.id.))
//                            startActivity(new Intent(this));
                        }
                    }
                });
    }

    private boolean validateEmail() {
        String email = TXTLemail.getEditableText().toString().trim();
        if (email.isEmpty()) {
            TXTLemail.setError("Field can't be empty!");
            return true;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                TXTLemail.setError("Please Enter Valid Email-Id");
                return true;
            }
            TXTLemail.setError(null);
            return false;
        }
    }
    private boolean validatePassword() {
        String password = TXTLpassword.getEditableText().toString().trim();
        if (password.length() < 6) {
            TXTLpassword.setError("Enter Complete Password!");
            return true;
        } else {
            TXTLpassword.setError(null);
            return false;
        }
    }
    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.BTNsignup:
                    startActivity(new Intent(this, Signup.class));
                    break;

                case R.id.BTNlogin:
                    if (validateEmail() | validatePassword()) {
                        return;
                    } else {
                        Toast.makeText(this, "Loggin in...", Toast.LENGTH_SHORT).show();
                        LoginProgress.setVisibility(View.VISIBLE);
                        startActivity(new Intent(this, HomePage.class));
                    }
                    break;

                case R.id.BTNgoogle:
//                    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//                    if(account != null){
//                        Toast.makeText(this, "Already SignedIN with this account!", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(this, HomePage.class));
//                    }else{
                    signIn();
//                    }
                    break;

                case R.id.TVBTNforgot:
                    startActivity(new Intent(this, ForgotPassword.class));
                    break;
            }

    }

}