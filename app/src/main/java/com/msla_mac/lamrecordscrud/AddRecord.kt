package com.msla_mac.lamrecordscrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddRecord : BaseActivity() {

    lateinit var edtEditRecordID: EditText
    lateinit var edtEditName: EditText
    lateinit var edtEditDescription: EditText
    lateinit var edtEditPrice: EditText
    lateinit var edtEditRating: EditText
    lateinit var edtEditDateModified: EditText
    lateinit var edtEditDateCreated: EditText

    private val dateFormatter = DateTimeFormatter.ofPattern("mm-dd-yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        edtEditRecordID = findViewById(R.id.edtEditRecordID)
        edtEditName = findViewById(R.id.edtEditName)
        edtEditDescription = findViewById(R.id.edtEditDescription)
        edtEditPrice = findViewById(R.id.edtEditPrice)
        edtEditRating = findViewById(R.id.edtEditRating)
        edtEditDateModified = findViewById(R.id.edtEditDateModified)
        edtEditDateCreated = findViewById(R.id.edtEditDateCreated)
    }

    fun addNewRecordOnClick( v : View) {
            val newRecordID = recordsList.size + 1
            val rating: Int = edtEditRating.text.toString().toInt()

        if (edtEditName.text == null || edtEditDescription.text == null || edtEditPrice.text == null || edtEditRating.text == null || (rating <= 0 || rating > 5)) {
            edtEditName.error = "Please enter valid name"
            edtEditDescription.error = "Please enter valid description"
            edtEditRating.error = "Please enter a rating between 1 and 5"
            edtEditPrice.error = "Please enter valid price"
        }
        else {
            val addRecordItem = RecordsItem(
                newRecordID,
                edtEditName.text.toString(),
                edtEditDescription.text.toString(),
                edtEditPrice.text.toString().toDouble(),
                edtEditRating.text.toString().toInt(),
                edtEditDateModified.toString(),
                edtEditDateCreated.toString()
            )

            recordsList.add(addRecordItem)
            appendRecordToFile(addRecordItem)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            toastIt("Successfully Added")
        }
    }

    fun showAllRecordsOnClick(v : View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}