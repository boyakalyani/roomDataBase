package com.example.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    //baiya lateinit var btn:Button
    //  lateinit var binding: ActivityMainBinding
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button

    lateinit var btn4: Button

    lateinit var edt1: EditText
    lateinit var edt2: EditText

//    lateinit var btn1:Button
//    lateinit var btn1:Button

    lateinit var adharDB: AdharDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)


        btn1 = findViewById(R.id.write_id)
        btn2 = findViewById(R.id.read_id)
        btn3 = findViewById(R.id.deleteAll_id)
        btn4 = findViewById(R.id.update_id)

        edt1 = findViewById(R.id.adharNumber_id)
        edt2 = findViewById(R.id.holderName_id)

        adharDB = AdharDataBase.getDatabase(this)
        //binding.write_id.setOnClickListener {
        btn1.setOnClickListener() {
            writeData()
        }
        //binding.read_id.setOnClickListener {
        btn2.setOnClickListener() {
            GlobalScope.launch {
                readData()
            }
        }
        btn3.setOnClickListener() {//btndeleteAll
            GlobalScope.launch {
                adharDB.adharDao().deleteAll()
            }
        }
        btn4.setOnClickListener() {//btnupdate
            updateData()
        }
    }

    private fun updateData() {
        // val adharNumber = binding.adharNumber_id.text.toString()
//        val AdharHolderName = binding.holderName_id.text.toString()
        val adharNumber = edt1.text.toString()
        val AdharHolderName = edt2.text.toString()

        if (adharNumber.isNotEmpty() && AdharHolderName.isNotEmpty()) {
//            val adharEntity = AdharEntity(
//                null, AdharHolderName, adharNumber.toInt())
            CoroutineScope(Dispatchers.IO).launch {
                adharDB.adharDao().update(
                    adharNum = adharNumber,
                    adharName = AdharHolderName,
                    adhar =adharNumber.toInt()
                )//two times rasav same check adhar entity
            }
//            binding.adharNumber_id.text.clear()
//            binding.holderName_id.text.clear()
            edt1.text.clear()
            edt2.text.clear()

            Toast.makeText(this@MainActivity, "Successfully updated", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this@MainActivity, "please enter data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun writeData() {
//        val adharNumber = binding.adharNumber_id.text.toString()
//        val AdharHolderName = binding.holderName_id.text.toString()
        val adharNumber = edt1.text.toString()
        val AdharHolderName = edt2.text.toString()

        if (adharNumber.isNotEmpty() && AdharHolderName.isNotEmpty()) {
            val adharEntity = AdharEntity(
                null, AdharHolderName, adharNumber.toInt())
            CoroutineScope(Dispatchers.IO).launch {
                adharDB.adharDao()
                    .insertAdharDetails(adharEntity)//two times rasav same check adhar entity
            }
//            binding.adharNumber_id.text.clear()
//            binding.holderName_id.text.clear()
            edt1.text.clear()
            edt2.text.clear()

            Toast.makeText(this@MainActivity, "Successfully written", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(this@MainActivity, "please enter data", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun displayData(adharEntity: AdharEntity) {
        withContext(Dispatchers.Main) {
//            binding.adharNumber_id.text = adharEntity.adharNumber
//            binding.holderName_id.text = adharEntity.adharHolderName
//            edt1.text = adharEntity.adharNumber
//            edt2.text =adharEntity.adharHolderName
            edt1.setText(adharEntity.adharNumber.toString())
            edt2.setText(adharEntity.adharHolderName.toString())

        }
    }

    private suspend fun readData() {
//        val adharCard=binding.adharNumber_id.text.toString()
        var adharCard:String = edt1.text.toString()
        if (adharCard.isNotEmpty()) {
            lateinit var adharEntit: AdharEntity
            CoroutineScope(Dispatchers.IO).launch {
                adharEntit = adharDB.adharDao().findByAdharNumber(adharCard.toInt())
                displayData(adharEntit)
            }
        }
    }
}


//        val sharedPreference=getSharedPreferences()
//        val dataBase=Room.databaseBuilder(this@MainActivity,AdharDataBase::class.java,"adhar_database")
//        btn=findViewById(R.id.btn1_id)
//        val adharEntity=AdharEntity(0,
//            "kalyani",
//            12-5-200,
//            "Anatapur",
//            "Female"
//        )
//        btn.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                dataBase.adharDao().insertAdharDetails(adharEntity)
//                Toast.makeText(this@MainActivity, "Data inserted Succeessfully", Toast.LENGTH_SHORT).show()
//                //dataBase.adharDao().insertAdhar()
//
//            }
//        }
//    }
//}