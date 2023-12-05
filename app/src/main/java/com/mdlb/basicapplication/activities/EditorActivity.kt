package com.mdlb.basicapplication.activities

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import com.mdlb.basicapplication.R
import com.mdlb.basicapplication.databases.DBHelper
import com.mdlb.basicapplication.databinding.ActivityEditorBinding
import java.util.ArrayList
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
    private lateinit var databaseConnection: DBHelper
    private var genderOtherSelected = false
    private var selectedHobbiesCount = 0
    private var selectedNoneHobbies = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseConnection = DBHelper.getInstance(this)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val isReadOnly = intent.getBooleanExtra("readonly", false);
        initInterface()
        setReadOnlyElementsIfNecessary(isReadOnly, intent)
        attachEventsListeners()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm")
        builder.setMessage("Are you sure to go back?")

        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            finish()
        }

        builder.setNegativeButton(android.R.string.no) { _, _ -> }

        this.onBackPressedDispatcher.addCallback(this) {
            builder.show()
        }

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
    private fun attachEventsListeners() {
        dateInputLayout.editText?.setOnClickListener {
            val inputDate: List<String> = dateInputLayout
                .editText?.text?.split("-") ?: ArrayList()
            val c = Calendar.getInstance()

            val inputYear = try {
                Integer.parseInt(
                    inputDate.getOrNull(0) ?: c.get(Calendar.YEAR).toString()
                )
            } catch (_: Exception) {
                c.get(Calendar.YEAR)
            }

            val inputMonth = try {
                (Integer.parseInt (
                    inputDate.getOrNull(1) ?: c.get(Calendar.MONTH).toString()
                ) - 1)
            } catch (_: Exception) {
                c.get(Calendar.MONTH)
            }

            val inputDay = try {
                Integer.parseInt(
                    inputDate.getOrNull(2) ?: c.get(Calendar.DAY_OF_MONTH).toString()
                )
            } catch (_: Exception) {
                c.get(Calendar.DAY_OF_MONTH)
            }

            val dateDialog = DatePickerDialog(this, { _, year, month, day ->
                var stringMonth = (month + 1).toString()
                if (stringMonth.length == 1) {
                    stringMonth = "0$stringMonth"
                }
                var stringDay = day.toString()
                if (stringDay.length == 1) {
                    stringDay = "0$stringDay"
                }
                dateInputLayout.editText?.setText("$year-$stringMonth-$stringDay")
            }, inputYear, inputMonth, inputDay)
            dateDialog.show()
        }

        timeInputLayout.editText?.setOnClickListener {
            val inputTime: List<String> = timeInputLayout
                .editText?.text?.split(":") ?: ArrayList()

            val inputHour = try {
                Integer.parseInt(
                    inputTime.getOrNull(0) ?: "0"
                )
            } catch (_: Exception) {
                0
            }

            val inputMinute = try {
                Integer.parseInt(
                    inputTime.getOrNull(1) ?: "0"
                )
            } catch (_: Exception) {
                0
            }

            val timeDialog = TimePickerDialog(this, { _, hour, minute ->
                var hourString = hour.toString()
                var minuteString = minute.toString()

                if (hourString.length == 1) {
                    hourString = "0$hourString"
                }

                if (minuteString.length == 1) {
                    minuteString = "0$minuteString"
                }
                timeInputLayout.editText?.setText(hourString + ":" + minuteString)
            }, inputHour, inputMinute, true)
            timeDialog.show()
        }

        (genderSpinner.editText as? MaterialAutoCompleteTextView)?.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, id ->
                // 4 Es "Other"
                if (id == 4L) {
                    genderTextInputLayout.visibility = View.VISIBLE
                    genderOtherSelected = true
                } else {
                    genderTextInputLayout.visibility = View.GONE
                    genderOtherSelected = false
                }
            }

        toggleCommentSwitch.setOnCheckedChangeListener {_, isChecked ->
            if (isChecked) {
                commentTextInputLayout.visibility = View.VISIBLE
            } else {
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
            var hasError = false
            val correctStringRegex = Regex("[A-Za-z0-9 ]+")
            val firstName = firstNameTextInputLayout.editText?.text.toString()

            if (correctStringRegex.matches(firstName) || firstName.isNotEmpty()) {
                firstNameTextInputLayout.error = null
            } else {
                firstNameTextInputLayout.error = "Invalid Name"
                hasError = true
            }

            if (!hasError) {
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "El formulario contiene errores, favor de revisar", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        databaseConnection.close()
        super.onDestroy()
    }
}