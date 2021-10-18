package no.lekrot.firestore_android_example.profile

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import no.lekrot.firestore_android_example.R
import no.lekrot.firestore_android_example.databinding.ActivityLoginBinding
import no.lekrot.firestore_android_example.databinding.ActivityProfileBinding
import no.lekrot.firestore_android_example.domain.Checkin

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        val userId = Firebase.auth.uid
        Firebase.firestore.document("users/$userId").addSnapshotListener { snapshot, exception ->
            if (snapshot == null || exception != null) return@addSnapshotListener
            val name = snapshot.getString("name")
            val city = snapshot.getString("city")
            binding.name.text = name
            binding.city.text = city
        }
        val adapter = UserAdapter(mutableListOf())
        binding.recycler.adapter = adapter
        Firebase.firestore.collection("checkins").whereEqualTo("userId", userId)
            .addSnapshotListener { collection, exception ->
                if (collection == null) return@addSnapshotListener
                val checkins = collection.documents.map { doc ->
                    Checkin(doc.getString("name")!!, doc.getTimestamp("date")!!.toDate().toString())
                }
                adapter.checkins.clear()
                adapter.checkins.addAll(checkins)
                adapter.notifyDataSetChanged()
            }
    }
}