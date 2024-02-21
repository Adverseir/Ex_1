package com.example.kostin_up

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setContentView(R.layout.activity_main2)
        var Button : Button = findViewById(R.id.button)
        var re :TextView = findViewById(R.id.textView5)
        var y = re.text.toString().toInt()
        y++
        re.text = y.toString()
        Button.setOnClickListener{

            var intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

 var textVie : TextView = findViewById(R.id.textView3)
            var s = textVie.text.toString().toInt()

            var count = 0

     var btnImg : ImageButton = findViewById(R.id.imageButton)
            btnImg.setOnClickListener{

                count++

                if(count === 1){
                    s++
                    btnImg.setImageResource(R.drawable.likeonn)
                }
                else{
                    count=0
                    s--
                    btnImg.setImageResource(R.drawable.likeofff)
                }
                textVie.text = s.toString()

            }

        var mess : TextView = findViewById(R.id.textView4)
        var b = mess.text.toString().toInt()

        var count2 = 0

        var messim : ImageButton = findViewById(R.id.imageView11)
            messim.setOnClickListener {
                count2++
                if(count2 ===1){
                    b++

                }
                else{
                    count2=0
                    b++

                }
                mess.text = b.toString()

                }
            }

}
fun toStringNumb(count:Int):String
{
    return when(count)
    {
        in 0..999 -> count.toString()
        in 1000..1_000_000 -> {
            ((count/100).toFloat()/10).toString() +"К"
        }
        in 1_000_000..1_000_000_000 -> {
            ((count/100_000).toFloat()/10).toString() + "М"
        }
        else -> "Более МЛРД"
    }
}







