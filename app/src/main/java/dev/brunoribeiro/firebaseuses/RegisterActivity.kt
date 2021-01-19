package dev.brunoribeiro.firebaseuses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.brunoribeiro.firebaseuses.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            createWithEmailPassword(binding.emailRegister.text.toString(), binding.passwordRegister.text.toString())
        }


        binding.btnLogin.setOnClickListener {
            callLogin()
        }
    }

    private fun createWithEmailPassword(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    updateUI(null)
                }
            }
    }


    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            callMain()
        } else {
            Log.e("Register", "Nenhuma conta conectada")
        }
    }



    private fun callMain(){
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }

    private fun callLogin(){
        val intent = Intent(this, LoginActivity::class.java)

        startActivity(intent)
    }
}