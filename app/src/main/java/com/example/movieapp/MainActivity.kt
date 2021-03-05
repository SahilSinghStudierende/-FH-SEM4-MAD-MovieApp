package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie = Movie("Irgendein Title", "Drama, Sport", 2.5f, "Sahil", "Max", "Hier ist eine Description")
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.movie = movie;

        val floatButton: FloatingActionButton = findViewById(R.id.floatButton)
        floatButton.setOnClickListener {floatButtonClick()}
    }

    private fun floatButtonClick() {
        Toast.makeText(this, "Float Button clicked", Toast.LENGTH_LONG).show()
    }
}
