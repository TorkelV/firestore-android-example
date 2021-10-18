package no.lekrot.firestore_android_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import no.lekrot.firestore_android_example.databinding.ActivityLoginBinding
import no.lekrot.firestore_android_example.databinding.ActivityProfileCreationBinding
import no.lekrot.firestore_android_example.domain.User
import no.lekrot.firestore_android_example.profile.ProfileActivity

class ProfileCreationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileCreationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.createProfileButton.setOnClickListener {
            val name: String = binding.name.text.toString()
            val city: String = binding.city.text.toString()
            val userId = Firebase.auth.currentUser?.uid
            if (name.isNotBlank() && city.isNotBlank() && !userId.isNullOrBlank()) {
                val user = User(userId, name, city)
                Firebase.firestore.document("users/$userId").set(user).addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}