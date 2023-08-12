package com.mdlb.basicapplication

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mdlb.basicapplication.databinding.ActivityEditorBinding

class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding
    private lateinit var calledBy: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calledBy = intent.getStringExtra("calledBy") ?: ""
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Toast.makeText(this, this.calledBy, Toast.LENGTH_LONG).show()
        return super.onTouchEvent(event)
    }
}