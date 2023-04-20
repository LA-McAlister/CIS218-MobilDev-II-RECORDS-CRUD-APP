package com.msla_mac.lamrecordscrud

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ShowRecord : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_record)

        val txtRecordID: TextView = findViewById(R.id.txtRecordID)
        val txtName: TextView = findViewById(R.id.txtName)
        val txtDescription: TextView = findViewById(R.id.txtDescription)
        val txtPrice: TextView = findViewById(R.id.txtPrice)
        val txtRating: TextView = findViewById(R.id.txtRating)
        val txtDateModified: TextView = findViewById(R.id.txtDateModified)
        val txtDateCreated: TextView = findViewById(R.id.txtDateCreated)

        val record = recordsList[ currentRecord ]

        txtRecordID.text = record.recordID.toString()
        txtName.text = record.name
        txtDescription.text = record.description
        txtPrice.text = record.price.toString()
        txtRating.text = record.rating.toString()
        txtDateModified.text = record.dateModified
        txtDateCreated.text = record.dateCreated
    }

    fun editRecordOnClick( v : View){
        val intent = Intent(this, EditRecord::class.java)
        startActivity(intent)
    }

    fun deleteRecordOnClick ( v: View){
        val builder = android.app.AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this record?").setCancelable(false)
            .setPositiveButton("Yes") { dialog, which ->
                toastIt("You want to delete the record")
                //delete the element from the list
                recordsList.removeAt(currentRecord)
                writeRecordsToFile()
                //Go to another screen - Show all activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("No"){ dialog, which ->
                toastIt("You canceled")
                dialog.cancel()
            }
            .create().show()
    }

    fun showAllRecordsOnClick(v : View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    } // End of showAll onClick
}