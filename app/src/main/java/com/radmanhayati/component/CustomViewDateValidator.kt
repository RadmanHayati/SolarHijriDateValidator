package com.radmanhayati.component

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.radmanhayati.solarhijridatevalidator.R
import com.radmanhayati.util.SolarCalendar

class CustomViewDateValidator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : LinearLayoutCompat(context, attrs, defStyle) {
    private var edtYear: EditText
    private var edtMonth: EditText
    private var edtDay: EditText
    var editTextYear: String = ""
        set(value) {
            field = value
            edtYear.setText(value)
        }
    var editTextMonth: String = ""
        set(value) {
            field = value
            edtMonth.setText(value)
        }
    var editTextDay: String = ""
        set(value) {
            field = value
            edtDay.setText(value)
        }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.costume_edit_text_date, this, true)
        orientation = VERTICAL
        // get views
        edtYear = findViewById(R.id.edtYear)
        edtMonth = findViewById(R.id.edtMonth)
        edtDay = findViewById(R.id.edtDay)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.custom_component_attributes, 0, 0
            )
            editTextYear = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.custom_component_attributes_year,
                        R.string.app_name
                    )
            ).toString()
            editTextMonth = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.custom_component_attributes_month,
                        R.string.app_name
                    )
            ).toString()
            editTextDay = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.custom_component_attributes_day,
                        R.string.app_name
                    )
            ).toString()
            typedArray.recycle()
        }
        filterInputs()
    }

    private fun filterInputs() {
        // handle day
        edtDay.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (edtDay.text.toString().trim().isNotEmpty() &&
                    edtDay.text.toString().trim().toInt() > 31
                ) {
                    val txt = edtDay.text.toString().trim()
                    edtDay.setText(txt.substring(0, txt.length - 1))
                    edtDay.setSelection(edtDay.length())
                }
                removeError()
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null
                    && s.toString().isNotEmpty()
                    && s.toString().length == 2
                    && s.toString().trim().toInt() <= 31
                ) {
                    edtMonth.requestFocus()
                }
            }
        })
        // handle month
        edtMonth.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (edtMonth.text.toString().trim().isNotEmpty() &&
                    edtMonth.text.toString().trim().toInt() > 12
                ) {
                    val txt = edtMonth.text.toString().trim()
                    edtMonth.setText(txt.substring(0, txt.length - 1))
                    edtMonth.setSelection(edtMonth.length())
                }
                removeError()
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null
                    && s.toString().isNotEmpty()
                    && s.toString().length == 2
                    && s.toString().trim().toInt() <= 12
                ) {
                    edtYear.requestFocus()
                }
            }
        })
        // handle year
        edtYear.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                removeError()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        // on complete
        edtYear.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Hide keyboard
                /*       // it can be done in the fragment directly
                       binding.btnEditProfileSubmit.requestFocus()*/
            }
            false
        }

    }

    fun isDateValid(): Boolean {
        var isDateValid = true
        if (edtDay.text.toString().trim().isEmpty()) {
            showError()
            isDateValid = false
        } else if (edtMonth.text.toString().trim().isEmpty()) {
            showError()
            isDateValid = false
        } else if (edtYear.text.toString().trim().isEmpty()
            || edtYear.text.toString().trim().length < 2
        ) {
            showError()
            isDateValid = false
        }
        return isDateValid
    }

    fun isDateValidBirthDay(): Boolean {
        val day = edtDay.toString().trim().toInt()
        val month = edtMonth.toString().trim().toInt()
        val year = edtYear.toString().trim().toInt()
        if (month < 1 || month > 12 || day < 1 || month < 7 && day > 31 || month > 6 && day > 30) return false
        //should be updated after years;
        val localYear = year + 1300;
        val milliSec = System.currentTimeMillis()
        val currentYear: Int = SolarCalendar.getShamsiYearFromMillis(milliSec).toInt()
        return localYear < currentYear - 18
        return false
    }

    fun showError() {

    }

    fun removeError() {

    }

}