package no.lekrot.firestore_android_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import no.lekrot.firestore_android_example.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.createUserButton.setOnClickListener {
            val email: String = binding.email.text.toString()
            val password: String = binding.password.text.toString()
            Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                Snackbar.make(
                    binding.root,
                    "Could not create user.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.loginButton.setOnClickListener {
            val email: String = binding.email.text.toString()
            val password: String = binding.password.text.toString()
            Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }.addOnFailureListener {
                Snackbar.make(
                    binding.root,
                    "Could not log in. Wrong email or password?",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        binding.forgotPwButton.setOnClickListener {
            val email: String = binding.email.text.toString()
            Firebase.auth.sendPasswordResetEmail(email).addOnCompleteListener {
                Snackbar.make(
                    binding.root,
                    "We've sent you an email with information on how to reset your password.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}