package tech.peny.snapchatclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        if(auth.currentUser != null){
            logIn()
        }

    }


    fun goClicked(view: View){
//  Check if we can log in the user
        auth.signInWithEmailAndPassword(emailEditText?.text.toString(),passwordEditText?.text.toString() )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                      logIn()
                    } else {
                        // If sign in fails, display a message to the user.
                        auth.createUserWithEmailAndPassword(emailEditText?.text.toString(),passwordEditText?.text.toString()).addOnCompleteListener(this){ task ->
                            if (task.isSuccessful){
//                                Add to database
                                logIn()

                            }else{
                                Toast.makeText(this,"Login Failed. Try AGAIN",Toast.LENGTH_SHORT).show()
                            }

                        }
                        // ...
                    }

                    // ...
                }

//        Sign up the user
    }

    fun logIn(){
//        Move to next activity
        val intent = Intent(this,SnapsActivity::class.java)
        startActivity(intent)
    }
}