package com.mdlb.basicapplication.activities

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.mdlb.basicapplication.R
import com.mdlb.basicapplication.databinding.ActivityEditorBinding

class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding
    private lateinit var calledBy: String
    private lateinit var firstNameEditBox: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        calledBy = intent.getStringExtra("calledBy") ?: ""
        firstNameEditBox = findViewById(R.id.firstNameTextInputLayout);
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Toast.makeText(this, this.calledBy + firstNameEditBox.editText?.text.toString(), Toast.LENGTH_LONG).show()
        return super.onTouchEvent(event)
    }
}