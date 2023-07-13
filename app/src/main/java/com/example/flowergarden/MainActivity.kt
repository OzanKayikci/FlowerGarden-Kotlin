package com.example.flowergarden

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.flowergarden.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var flowerList: ArrayList<Flower> = ArrayList<Flower>()

    /*
    * Adapter'ler, veri tabanından veya ağdan gelen verileri kullanıcı arayüzünde listeleyerek dinamik ve esnek bir kullanıcı deneyimi sağlar.
    * Veri değişiklikleri olduğunda, Adapter otomatik olarak güncellenir ve değişiklikleri kullanıcı arayüzüne yansıtır.
    *  Böylece, kullanıcı arayüzünde tutarlı ve doğru bir görüntü sağlanır.
    * */
    var adapter: FlowersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setFlowersToView(binding)
    }


    fun setFlowersToView(binding: ActivityMainBinding){
        //get flowers from json
        val resourceId = R.raw.flowers
        val flowers = getFlowers(this,resourceId).convertToObj()

        for (flower in flowers){
            val imageResource = resources.getIdentifier(flower.image!!, "drawable", packageName)

            flowerList.add(Flower(flower.name!!, flower.description!!, imageResource))

        }

        adapter = FlowersAdapter(this, flowerList)
        binding.listView.adapter = adapter
    }


    class FlowersAdapter : BaseAdapter {
        var tempList: ArrayList<Flower> = ArrayList<Flower>()
        var context: Context? = null

        constructor(context: Context, tempList: ArrayList<Flower>) : super() {
            this.context = context
            this.tempList = tempList
        }

        override fun getCount(): Int {
            return tempList.size
        }

        override fun getItem(position: Int): Any {
            return tempList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()


        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var posFlowers = tempList[position]
            var inflater =
                context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            var flowerCard = inflater.inflate(R.layout.flower_card, null, false)

            flowerCard.findViewById<TextView>(R.id.cardName).text = posFlowers.name
            flowerCard.findViewById<TextView>(R.id.cardDesc).text = posFlowers.desc
            flowerCard.findViewById<ImageView>(R.id.cardImage).setImageResource(posFlowers.image!!)

            flowerCard.findViewById<LinearLayout>(R.id.cardLayout).setOnClickListener{
                var intent = Intent(context,FlowerDetail::class.java)
                intent.putExtra("name",posFlowers.name)
                intent.putExtra("desc",posFlowers.desc)
                intent.putExtra("image",posFlowers.image!!)
                context!!.startActivity(intent)
            }
            return flowerCard

        }

    }
}