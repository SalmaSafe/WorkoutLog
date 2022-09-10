package Isoul.Safe.workoutlog.ui

import Isoul.Safe.workoutlog.R
import Isoul.Safe.workoutlog.databinding.ActivityLoginBinding
import Isoul.Safe.workoutlog.models.LoginRequest
import Isoul.Safe.workoutlog.models.LoginResponse
import Isoul.Safe.workoutlog.retrofit.ApiClient
import Isoul.Safe.workoutlog.retrofit.ApiInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs:SharedPreferences
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
            makeLoginRequest(loginRequest)
        }


    }

    fun makeLoginRequest(loginRequest: LoginRequest)
    {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.login(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.pbLogin.visibility=View.GONE

                if(response.isSuccessful){
                    val loginResponse = response.body()
                    saveLoginDetails(loginResponse!!)
                    Toast.makeText(baseContext,response.body()?.message,Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                    finish()
                }else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility = View.GONE
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()
    }
}