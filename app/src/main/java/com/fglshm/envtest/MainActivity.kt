package com.fglshm.envtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseDatabase.getInstance().reference.child("log").child(BuildConfig.ENVIRONMENT)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    text_count.text = dataSnapshot.childrenCount.toString()
                }

            })

        button_save.setOnClickListener {
            FirebaseDatabase.getInstance().reference.child("log").child(BuildConfig.ENVIRONMENT)
                .child(UUID.randomUUID().toString()).setValue(Date())
        }

    }
}
