package com.msla_mac.lamrecordscrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class EditRecord : BaseActivity() {

    lateinit var edtEditRecordID: EditText
    lateinit var edtEditName: EditText
    lateinit var edtEditDescription: EditText
    lateinit var edtEditPrice: EditText
    lateinit var edtEditRating: EditText
    lateinit var edtEditDateModified: EditText
    lateinit var edtEditDateCreated: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_record)
        val record = recordsList[currentRecord]

        edtEditRecordID = findViewById(R.id.edtEditRecordID)
        edtEditName = findViewById(R.id.edtEditName)
        edtEditDescription = findViewById(R.id.edtEditDescription)
        edtEditPrice = findViewById(R.id.edtEditPrice)
        edtEditRating = findViewById(R.id.edtEditRating)
        edtEditDateModified = findViewById(R.id.edtEditDateModified)
        edtEditDateCreated = findViewById(R.id.edtEditDateCreated)

        //setting textEdit to defualt values
        edtEditRecordID.setText(record.recordID.toString())
        edtEditName.setText(record.name)
        edtEditDescription.setText(record.description)
        edtEditPrice.setText(record.price.toString())
        edtEditRating.setText(record.rating.toString())
        edtEditDateModified.setText(record.dateModified)
        edtEditDateCreated.setText(record.dateCreated)
    }

    fun editRecordsOnClick(v : View) {
        val record = recordsList[currentRecord]
        val rating: Int = edtEditRating.text.toString().toInt()

        if (edtEditName.text == null || edtEditDescription.text == null || edtEditPrice.text == null || edtEditRating.text == null || (rating <= 0 || rating > 5)) {
        edtEditName.error = "Please enter valid name"
        edtEditDescription.error = "Please enter valid description"
        edtEditRating.error = "Please enter a rating between 1 and 5"
        edtEditPrice.error = "Please enter valid price"
    }
    else {
        record.name = edtEditName.text.toString()
        record.description = edtEditDescription.text.toString()
        record.price = edtEditPrice.text.toString().toDouble()
        record.rating = edtEditRating.text.toString().toInt()
        recordsList[currentRecord] = record

        //writing back to file
        writeRecordsToFile()
        toastIt("Item Updated")
        val intent = Intent(this, ShowRecord::class.java)
        startActivity(intent)
        }
    }


    fun showAllRecordsOnClick(v : View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}