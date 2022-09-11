package Isoul.Safe.workoutlog.ui

import Isoul.Safe.workoutlog.R
import Isoul.Safe.workoutlog.databinding.ActivityLoginBinding
import Isoul.Safe.workoutlog.models.LoginRequest
import Isoul.Safe.workoutlog.models.LoginResponse
import Isoul.Safe.workoutlog.api.ApiClient
import Isoul.Safe.workoutlog.api.ApiInterface
import Isoul.Safe.workoutlog.viewmodel.UserViewModel
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs:SharedPreferences
    val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs= getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)


        binding.btnLogin.setOnClickListener {
            validateLogin()


        }
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }

    private fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etSPassword.text.toString()
        var error = false


        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_required)
            error = true
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (!error) {
            var loginRequest = LoginRequest(email, password)
            binding.pbLogin.visibility=View.VISIBLE
            userViewModel.loginUser(loginRequest)
        }

    }
    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()
    }
}