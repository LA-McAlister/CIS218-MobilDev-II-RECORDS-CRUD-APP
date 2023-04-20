package com.msla_mac.lamrecordscrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class AddRecord : BaseActivity() {

    lateinit var edtEditRecordID: EditText
    lateinit var edtEditName: EditText
    lateinit var edtEditDescription: EditText
    lateinit var edtEditPrice: EditText
    lateinit var edtEditRating: EditText
    lateinit var edtEditDateModified: EditText
    lateinit var edtEditDateCreated: EditText

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

    fun addNewEventOnClick( v : View){
        val recordItem = RecordsItem(
            edtEditRating.text.toString().toInt(),
            edtEditName.text.toString(),
            edtEditDescription.text.toString(),
            edtEditPrice.text.toString().toDouble(),
            edtEditRating.text.toString().toInt(),
            edtEditDateModified.text.toString(),
            edtEditDateCreated.text.toString()
        )
            recordsList.add(recordItem)

        appendRecordToFile( recordItem )

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}