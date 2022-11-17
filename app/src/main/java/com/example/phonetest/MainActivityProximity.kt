package com.example.phonetest

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivityProximity : AppCompatActivity() {
    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_proximity)
        var txt = findViewById<TextView>(R.id.proximityStatusTxt)



        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null){
            proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            sensorManager.registerListener(proximitySensorEventListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }else{

            Toast.makeText(this, "Proximity Sensor is not Available", Toast.LENGTH_SHORT).show()
            finish()
        }










    }

    private var proximitySensorEventListener : SensorEventListener? = object :SensorEventListener{


        override fun onSensorChanged(p0: SensorEvent?) {
            if (p0 != null) {
                if (p0.sensor?.type == Sensor.TYPE_PROXIMITY){
                    if (p0.values[0] == 0f){
                        window.decorView.setBackgroundColor(Color.GREEN)
                        setText(this@MainActivityProximity, "ON")

                    }else{
                        window.decorView.setBackgroundColor(Color.RED)
                        setText(this@MainActivityProximity, "OFF")
                    }
                }

            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            //
        }

    }

    private fun setText(mainActivityProximity: MainActivityProximity, s: String) {

        var msg = "Proximity Sensor is " + s

        var txt = findViewById<TextView>(R.id.proximityStatusTxt)
        txt.text = msg



    }


    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(proximitySensorEventListener)
    }


}



