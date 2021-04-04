package com.example.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var drawLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val findNavController = this.findNavController(R.id.navHost)

        drawLayout = contentView.drawLayout
        NavigationUI.setupActionBarWithNavController(this, findNavController, drawLayout)
        NavigationUI.setupWithNavController(contentView.navView, findNavController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val findNavController = this.findNavController(R.id.navHost)

        return NavigationUI.navigateUp(findNavController, drawLayout)
    }

}
