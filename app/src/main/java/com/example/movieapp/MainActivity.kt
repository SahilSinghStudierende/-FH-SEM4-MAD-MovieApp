package com.example.movieapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var drawLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val findNavController = this.findNavController(R.id.detailFragment)

        drawLayout = contentView.drawLayout
        NavigationUI.setupActionBarWithNavController(this, findNavController, drawLayout)
        NavigationUI.setupWithNavController(contentView.navView, findNavController)


        val floatButton: FloatingActionButton = findViewById(R.id.floatButton)
        floatButton.setOnClickListener { floatButtonClick() }


    }

    override fun onSupportNavigateUp(): Boolean {
        val findNavController = this.findNavController(R.id.detailFragment)

        return NavigationUI.navigateUp(findNavController, drawLayout)
    }

    /**
     * Show toast on float button click
     */
    private fun floatButtonClick() {
        Toast.makeText(this, "Float Button clicked", Toast.LENGTH_LONG).show()
    }



}
