package com.dapzthelegend.tictactoe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Called when the application is started i.e Launch Activity. Activity class for single
 * activity design. Activity for the Nav Host Fragment.
 *
 * @see AppCompatActivity
 */
class SampleMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_main)
    }
}
