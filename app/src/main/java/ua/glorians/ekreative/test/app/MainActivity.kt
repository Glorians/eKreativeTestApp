package ua.glorians.ekreative.test.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ua.glorians.ekreative.test.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFields() {
    }

    private fun initFunc() {}
    
}