package no.lekrot.firestore_android_example.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import no.lekrot.firestore_android_example.R
import no.lekrot.firestore_android_example.domain.Checkin
import no.lekrot.firestore_android_example.domain.User

class UserAdapter(val checkins: MutableList<Checkin>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val date: TextView = view.findViewById(R.id.date)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.checkin, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val checkin: Checkin = checkins[position]
        viewHolder.name.text = checkin.name
        viewHolder.date.text = checkin.date
    }

    override fun getItemCount() = checkins.size

}
