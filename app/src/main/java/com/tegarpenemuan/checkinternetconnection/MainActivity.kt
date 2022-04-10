package com.tegarpenemuan.checkinternetconnection

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var context = this
    var connectivity: ConnectivityManager? = null
    var info: NetworkInfo? = null

    private lateinit var btnCheckConnection: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        listener()
//        isNetworkAvailable(this)
    }

    override fun onStart() {
        super.onStart()
        koneksi()
    }

//    private fun isNetworkAvailable(context: Context): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val nw = connectivityManager.activeNetwork ?: return false
//            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
//            return when {
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                //for other device how are able to connect with Ethernet
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                //for check internet over Bluetooth
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
//                else -> false
//            }
//        } else {
//            return connectivityManager.activeNetworkInfo?.isConnected ?: false
//        }
//    }


    private fun listener() {
        btnCheckConnection = findViewById(R.id.btnCekKoneksi)
        btnCheckConnection.setOnClickListener {
            connectivity =
                context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (connectivity != null) {
                info = connectivity!!.activeNetworkInfo

                if (info != null) {

                    if (info!!.state == NetworkInfo.State.CONNECTED) {
                        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun koneksi() {
        btnCheckConnection = findViewById(R.id.btnCekKoneksi)
        connectivity =
            context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivity != null) {
            info = connectivity!!.activeNetworkInfo

            if (info != null) {

                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    btnCheckConnection.visibility = View.GONE
                    supportActionBar!!.title = "Ada Koneksi Internet"
                }
            } else {
                supportActionBar!!.title = "Tidak Ada Koneksi"
                btnCheckConnection.visibility = View.VISIBLE
                Toast.makeText(this, "Not Connected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}