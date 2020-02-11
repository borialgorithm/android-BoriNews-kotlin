package com.bori.borinews.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.core.net.ConnectivityManagerCompat
import com.bori.borinews.R

class ConnectionManager private constructor()
{
    companion object
    {
        fun isNetworkAvailable(context: Context): Boolean
        {
            val connectivityManager: ConnectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false

                return when
                {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
            else
            {
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            }
        }

        fun connectionCheck(context: Context)
        {
           if(isNetworkAvailable(context))
           {
           }
           else
           {
               val msg = context.getString(R.string.no_network)
               Toast.makeText(context,msg,Toast.LENGTH_LONG)

           }
        }

    }


}

