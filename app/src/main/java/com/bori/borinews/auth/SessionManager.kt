package com.bori.borinews.auth

import android.app.Activity
import android.content.ContentProviderClient
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.bori.borinews.R
import com.bori.borinews.user.User
import com.google.firebase.auth.FirebaseAuth
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kotlin.Unit

class SessionManager private constructor(var context: Context)
{
    companion object
    {
        @Volatile private var INSTANCE: SessionManager? = null

        const val IS_LOGIN: String = "isLogin"

        const val KEY_NAME: String ="name"
        const val KEY_EMAIL: String = "email"
        const val KEY_SCREEN_NAME = "screenName"
        const val KEY_PROFILEPIC = "profilePic"

        const val PREF_NAME: String = "login_pref"
        const val PRIVATE_MODE: Int = 0

        fun getInstance(context: Context): SessionManager?
        {
            if(INSTANCE == null)
            {
                synchronized(this)
                {
                    INSTANCE = SessionManager(context)
                }
            }

            return INSTANCE
        }
    }

    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private lateinit var twitterAuthClient: TwitterAuthClient
    private var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    init
    {
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = preferences?.edit()
        auth = FirebaseAuth.getInstance()
        authStateListener = FirebaseAuth.AuthStateListener(object : FirebaseAuth.AuthStateListener,
                (FirebaseAuth) -> Unit
        {
            override fun onAuthStateChanged(p0: FirebaseAuth)
            {
                val user = p0.currentUser
                if(user != null)
                {
                    Log.i("TWTSESSION", "Uset is valid")
                }
                else
                {
                    Log.i("TWTSESSION", "failed")

                }

            }

            override fun invoke(p1: FirebaseAuth)
            {

            }
        })

        auth.addAuthStateListener(authStateListener)


    }

    fun createLoginSession(user: User)
    {
        editor?.putBoolean(IS_LOGIN, true)
        editor?.putString(KEY_NAME, user.name)
        editor?.putString(KEY_SCREEN_NAME, user.screenName)
        editor?.putString(KEY_EMAIL, user.email)
        editor?.putString(KEY_PROFILEPIC, user.profileUrl)

        editor?.commit()

    }


    fun twitterSettingInit()
    {
       val authConfig = TwitterAuthConfig(
           context.getString(R.string.twt_api_key),
           context.getString(R.string.twt_api_secret_key)
       )

        val twitterConfig = TwitterConfig.Builder(context)
            .twitterAuthConfig(authConfig)
            .build()
        Twitter.initialize(twitterConfig)

        twitterAuthClient = TwitterAuthClient()

    }

    fun twitterLogin()
    {
        twitterAuthClient?.authorize(
            context as Activity?,
            object : Callback<TwitterSession>()
            {
                override fun success(result: Result<TwitterSession>?)
                {
                    if(result != null)
                    {
                        val my = 10
                        Toast.makeText(context,"login", Toast.LENGTH_LONG )

                    }
                }

                override fun failure(exception: TwitterException?)
                {
                    val my2 = 10
                    Toast.makeText(context,"Fail to login", Toast.LENGTH_LONG )

                }
            }
        )


    }

    fun twitterLogout()
    {
        auth.signOut()
        TwitterCore.getInstance().sessionManager.clearActiveSession()

    }

    fun isLoggedIn(): Boolean?
    {
        return preferences?.getBoolean(IS_LOGIN, false)
    }

}
