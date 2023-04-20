package com.msla_mac.lamrecordscrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : BaseActivity() {
    lateinit var recyclerView : RecyclerView
    private lateinit var recordsListAdapter: RecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recordsRecycler)
        recordsListAdapter = RecordsAdapter(recordsList) { position ->
            toastIt("You selected position: $position")

            //perform whatever you want onclick
            val intent = Intent(this, ShowRecord::class.java)
            currentRecord = position

            startActivity(intent)

        }
        toastIt("Created list")

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = recordsListAdapter

        readRecordsFile()
        recordsListAdapter.notifyDataSetChanged()
    }
    fun addRecordOnClick( v : View ) {
        val intent = Intent(this, AddRecord::class.java)
        startActivity(intent)
    }


    fun addMockDataOnClick( v : View) {
        for (i in 1..41) {
            val recordsItem = RecordsItem(i,"Meeting $i", "Very long meeting", 13.99, 1, "4/19/2023", "3/22/2023")
            recordsList.add(recordsItem)
        }
        writeRecordsToFile()
        recordsListAdapter.notifyDataSetChanged()
    }
}