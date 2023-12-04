package com.mdlb.basicapplication.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputLayout
import com.mdlb.basicapplication.R
import com.mdlb.basicapplication.databinding.ActivityEditorBinding
import java.util.Calendar
import kotlin.properties.Delegates

class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding
    private lateinit var firstNameTextInputLayout: TextInputLayout
    private lateinit var lastNameTextInputLayout: TextInputLayout
    private lateinit var dateInputLayout: TextInputLayout
    private lateinit var timeInputLayout: TextInputLayout
    private lateinit var genderSpinner: TextInputLayout
    private lateinit var genderTextInputLayout: TextInputLayout
    private lateinit var levelRadioGroup: RadioGroup
    private lateinit var toggleCommentSwitch: MaterialSwitch
    private lateinit var commentTextInputLayout: TextInputLayout
    private lateinit var preference01CheckBox: CheckBox
    private lateinit var preference02CheckBox: CheckBox
    private lateinit var preference03CheckBox: CheckBox
    private lateinit var preference04CheckBox: CheckBox
    private lateinit var preference05CheckBox: CheckBox
    private lateinit var preference06CheckBox: CheckBox
    private lateinit var preference07CheckBox: CheckBox
    private lateinit var preferenceOtherCheckBox: CheckBox
    private lateinit var preferenceNoneCheckBox: CheckBox
    private lateinit var saveButton: Button

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val isReadOnly = intent.getBooleanExtra("readonly", false);
        initInterface()
        setReadOnlyElementsIfNecessary(isReadOnly, intent)
        attachEventsListeners()
    }

    /**
     * Initialize the class properties declared with late initialization
     */
    private fun initInterface() {
        firstNameTextInputLayout = findViewById(R.id.firstNameTextInputLayout)
        lastNameTextInputLayout = findViewById(R.id.lastNameTextInputLayout)
        dateInputLayout = findViewById(R.id.dateInputLayout)
        timeInputLayout = findViewById(R.id.timeInputLayout)
        genderSpinner = findViewById(R.id.genderSpinner)
        genderTextInputLayout = findViewById(R.id.genderTextInputLayout)
        levelRadioGroup = findViewById(R.id.levelRadioGroup)
        toggleCommentSwitch = findViewById(R.id.toggleCommentMaterialSwitch)
        commentTextInputLayout = findViewById(R.id.commentTextInputLayout)
        preference01CheckBox = findViewById(R.id.preference01CheckBox)
        preference02CheckBox = findViewById(R.id.preference02CheckBox)
        preference03CheckBox = findViewById(R.id.preference03CheckBox)
        preference04CheckBox = findViewById(R.id.preference04CheckBox)
        preference05CheckBox = findViewById(R.id.preference05CheckBox)
        preference06CheckBox = findViewById(R.id.preference06CheckBox)
        preference07CheckBox = findViewById(R.id.preference07CheckBox)
        preferenceOtherCheckBox = findViewById(R.id.preference08CheckBox)
        preferenceNoneCheckBox = findViewById(R.id.preference09CheckBox)
        saveButton = findViewById(R.id.saveButton)
    }

    /**
     * Set all the controllers in this view to ReadOnly
     */
    private fun setReadOnlyElementsIfNecessary(readOnly: Boolean, intent: Intent) {
        if (!readOnly) return;
        val elementId = intent.getStringExtra("elementId");
    }

    /**
     * Attach every event listeners to every component that needed
     */
    @RequiresApi(Build.VERSION_CODES.N)
    private fun attachEventsListeners() {
        dateInputLayout.editText?.setOnClickListener {
            Toast.makeText(this, "Click en DateBirth", Toast.LENGTH_LONG).show()
            val c = Calendar.getInstance()
            val currentYear = c.get(Calendar.YEAR)
            val currentMonth = c.get(Calendar.MONTH)
            val currentDay = c.get(Calendar.DAY_OF_MONTH)
            val dateDialog = DatePickerDialog(this, { _, year, month, day ->
                dateInputLayout.editText?.setText(year.toString() + "-" + month.toString() + "-" + day.toString())
            }, currentYear, currentMonth, currentDay)
            dateDialog.show()
        }

        timeInputLayout.editText?.setOnClickListener {
            val timeDialog = TimePickerDialog(this, { _, hour, minute ->
                timeInputLayout.editText?.setText(hour.toString() + ":" + minute.toString())
            }, 0, 0, true)
            timeDialog.show()
        }

        toggleCommentSwitch.setOnCheckedChangeListener {_, isChecked ->
            if (isChecked) {
                commentTextInputLayout.visibility = View.VISIBLE
            } else {
                commentTextInputLayout.editText?.setText("")
                commentTextInputLayout.visibility = View.GONE
            }
        }

        preferenceNoneCheckBox.setOnCheckedChangeListener {_ , isChecked ->
            if (isChecked) {
                preference01CheckBox.isEnabled = false
                preference02CheckBox.isEnabled = false
                preference03CheckBox.isEnabled = false
                preference04CheckBox.isEnabled = false
                preference05CheckBox.isEnabled = false
                preference06CheckBox.isEnabled = false
                preference07CheckBox.isEnabled = false
                preferenceOtherCheckBox.isEnabled = false
            } else {
                preference01CheckBox.isEnabled = true
                preference02CheckBox.isEnabled = true
                preference03CheckBox.isEnabled = true
                preference04CheckBox.isEnabled = true
                preference05CheckBox.isEnabled = true
                preference06CheckBox.isEnabled = true
                preference07CheckBox.isEnabled = true
                preferenceOtherCheckBox.isEnabled = true
            }
        }

        saveButton.setOnClickListener {

            Toast.makeText(this, "Registro guardado", Toast.LENGTH_LONG).show()
        }
    }
}