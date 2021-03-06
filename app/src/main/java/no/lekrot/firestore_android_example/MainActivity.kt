package no.lekrot.firestore_android_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import no.lekrot.firestore_android_example.profile.ProfileActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userId: String? = Firebase.auth.currentUser?.uid
        if (Firebase.auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Firebase.firestore.document("users/$userId").get().addOnSuccessListener { doc ->
                if (doc.exists()) {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, ProfileCreationActivity::class.java))
                    finish()
                }
            }
        }
    }
}