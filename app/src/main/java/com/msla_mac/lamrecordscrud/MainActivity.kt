package com.msla_mac.lamrecordscrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

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

    fun editRecordOnClick(v : View) {
        val intent = Intent(this, EditRecord::class.java)
        startActivity(intent)
    } // End of editEvent onClick

    fun addRecordOnClick( v : View ) {
        val intent = Intent(this, AddRecord::class.java)
        startActivity(intent)
    }

    fun addMockDataOnClick( v : View) {
        for (i in 1..41) {
            val rating = (1..5).random()
            val price = Random.nextDouble(999.99)
            val recordsItem = RecordsItem(i,"Meeting $i", "Very long meeting", price, rating, "19 April 2023", "19 March 2023")
            recordsList.add(recordsItem)
        }
        writeRecordsToFile()
        recordsListAdapter.notifyDataSetChanged()
    }

    fun scrollToTopOnClick(v : View) {
        recyclerView.smoothScrollToPosition(0)
    }

    fun scrollToBottomOnClick(v : View) {
        recyclerView.smoothScrollToPosition(recordsList.size)
    }

    fun clearAllDataOnClick(view : View) {
        deleteRecordsFile()
    }

}