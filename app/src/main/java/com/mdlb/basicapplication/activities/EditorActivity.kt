package com.mdlb.basicapplication.activities

import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputLayout
import com.mdlb.basicapplication.R
import com.mdlb.basicapplication.databinding.ActivityEditorBinding

class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding
    private lateinit var firstNameTextInputLayout: TextInputLayout
    private lateinit var lastNameTextInputLayout: TextInputLayout
    private lateinit var dateInputLayout: TextInputLayout
    private lateinit var timeInputLayout: TextInputLayout
    private lateinit var genderSpinner: Spinner
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
    private lateinit var saveButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initInterface()
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
     * Attach every event listeners to every component that needed
     */
    private fun attachEventsListeners() {

    }
}