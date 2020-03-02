package com.bori.borinews.auth.activity.ui.login

import android.app.Activity
import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ActionMode
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.bori.borinews.R
import com.bori.borinews.auth.SessionManager
import com.bori.borinews.connection.ConnectionManager
import com.bori.borinews.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterLoginButton


class LoginActivity : AppCompatActivity()
{
    private val TAG: String = "LoginActivity"

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var context: Context
    private lateinit var user: User
    private var session: SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        context = applicationContext
        session = SessionManager.getInstance(this)
        session?.twitterSettingInit()

        setContentView(R.layout.activity_login)

        session?.firebaseAuthInit()

        setUser()
        ConnectionManager.connectionCheck(this)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        login.setOnClickListener {
            session?.twitterLogin()
        }

        val twtLogin = findViewById<TwitterLoginButton>(R.id.twt_login_button)

        twtLogin.setCallback(object: Callback<TwitterSession>()
        {
            override fun success(result: Result<TwitterSession>?)
            {
                Log.d(TAG, "success login")
                Toast.makeText(applicationContext, "success to login", Toast.LENGTH_LONG)
                    .show()

            }

            override fun failure(exception: TwitterException?)
            {
                Toast.makeText(applicationContext, "fail to login", Toast.LENGTH_LONG).show()
                Log.d(TAG, "fail to login")
                Log.d(TAG, exception.toString() )
            }
        })

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null)
            {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null)
            {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null)
            {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null)
            {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId)
                {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            /*
            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())


            } */
        }
    }

    private fun setUser()
    {
        user = User()
    }


    private fun updateUiWithUser(model: LoggedInUserView)
    {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int)
    {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun onStart()
    {
        super.onStart()
        session?.addFirebaseListener()
    }

    override fun onDestroy()
    {
        super.onDestroy()
        session?.removeFirebaseListener()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit)
{
    this.addTextChangedListener(object : TextWatcher
    {
        override fun afterTextChanged(editable: Editable?)
        {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
        {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
        {
        }
    })
}

