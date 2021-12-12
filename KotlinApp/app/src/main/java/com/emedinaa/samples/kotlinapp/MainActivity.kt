package com.emedinaa.samples.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Eduardo Medina
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button).setOnClickListener {
            gotoDemoActivity()
        }
    }

    private fun gotoDemoActivity() {
        startActivity(Intent(this, DemoActivity::class.java))
    }
}