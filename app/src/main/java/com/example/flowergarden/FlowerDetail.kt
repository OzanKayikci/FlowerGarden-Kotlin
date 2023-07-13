package com.example.flowergarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.flowergarden.databinding.ActivityFlowerDetailBinding
import com.example.flowergarden.databinding.ActivityMainBinding

class FlowerDetail : AppCompatActivity() {
    lateinit var binding: ActivityFlowerDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_flower_detail)
        binding = ActivityFlowerDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //putExtradaki verileri çekebilmek için
        var bundle:Bundle = intent.extras!!

        var name:String? = bundle.getString("name")
        var desc:String? = bundle.getString("desc")
        var image:Int? = bundle.getInt("image")
        println("narm"+ name+image+desc)
        view.findViewById<TextView>(R.id.flowerName).text = name!!
        view.findViewById<TextView>(R.id.flowerDescription).text = desc!!
        view.findViewById<ImageView>(R.id.flowerImage).setImageResource(image!!)

    }
}