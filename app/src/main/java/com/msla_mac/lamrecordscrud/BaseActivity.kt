package com.msla_mac.lamrecordscrud

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

var recordsList = ArrayList<RecordsItem>()
var currentRecord = 0
const val RECORDS_FILE = "records.txt"

open class BaseActivity() : AppCompatActivity() {

    fun writeRecordsToFile(){
        //Open File
        val fileOutputStream: FileOutputStream = openFileOutput(RECORDS_FILE, MODE_PRIVATE)
        val recordFile = OutputStreamWriter(fileOutputStream)
        //Write each record
        for(record in recordsList) {
            recordFile.write( record.toCSV() + "\n")
        }
        // Close the File
        recordFile.close()
    }

    fun readRecordsFile(){
        recordsList.clear()
        //Open the file
        val file = File(filesDir, RECORDS_FILE)
        if(file.exists()) {
            //Read each line
            File(filesDir, RECORDS_FILE). forEachLine{
                val parts = it.split(",")
                //convert to an RecordsItem
                val record = RecordsItem(parts[0].toInt(), parts[1], parts[2], parts[3].toDouble(), parts[4].toInt(), parts[5], parts[6] )
                //Add the event to the recordList
                recordsList.add(record)
            }
        }
    }

    fun deleteRecordsFile(){
        val file = File(filesDir, RECORDS_FILE)
        if(file.exists()){
            file.delete()
        }
    }

    fun appendRecordToFile(record: RecordsItem) {
        val fileOutputStream: FileOutputStream = openFileOutput(RECORDS_FILE, MODE_APPEND)
        val recordFile = OutputStreamWriter(fileOutputStream)
        recordFile.write( "${record.toCSV()}\n")
        recordFile.close()
    }

    open fun toastIt(msg: String?) {
        Toast.makeText(
            applicationContext,
            msg, Toast.LENGTH_SHORT
        ).show()
    }
}