package Isoul.Safe.workoutlog.ui

import Isoul.Safe.workoutlog.databinding.ActivitySignupBinding
import Isoul.Safe.workoutlog.models.RegisterRequest
import Isoul.Safe.workoutlog.models.RegisterResponse
import Isoul.Safe.workoutlog.api.ApiClient
import Isoul.Safe.workoutlog.api.ApiInterface
import Isoul.Safe.workoutlog.viewmodel.UserViewModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignUp.setOnClickListener {
            validateSignUp()
        }
        binding.tvSignIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        userViewModel.signUpLiveData.observe(this, Observer { signUpResponse ->
            Toast.makeText(baseContext, signUpResponse?.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })
        userViewModel.signUpErrorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }

//    override fun onResume() {
//        super.onResume()
//        userViewModel.signUpResponseLiveData.observe(this, Observer { signUpResponse ->
//            saveSignUpDetails(signUpResponse!!)
//            Toast.makeText(baseContext, signUpResponse?.message, Toast.LENGTH_LONG).show()
//            startActivity(Intent(baseContext, HomeActivity::class.java))
//            finish()
//        })
//        userViewModel.signUpErrorLiveData.observe(this, Observer { error ->
//            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//        })
    private fun validateSignUp(){
        var firstName= binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var email= binding.etSEmail.text.toString()
        var phoneNumber = binding.etPhone.toString()
        var sPassword= binding.etSPassword .text.toString()
        var confirm= binding.etConfirmP .text.toString()

        var error =false

        if (firstName.isBlank()){
            binding.tilFirstName.error= "First Name is required"
            error=true
        }

        if (lastName.isBlank()){
            error=true
            binding.tilLastName.error ="Last name is required"
        }

        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhone.error = "PhoneNumber is required"
        }
        if (sPassword.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if (!confirm.equals(sPassword)){
            error=true
            binding.tilConfirmP.error =" Password doesn't match";

        }
        if (!error) {
            val registerRequest= RegisterRequest(firstName, lastName, email,phoneNumber,sPassword)
//            makeRegistrationRequest(registerRequest)
            userViewModel.signUpUser(registerRequest)
        }
        else{
            binding.tilConfirmP.error="Password Invalid"

        }

    }


//    fun makeRegistrationRequest(registerRequest: RegisterRequest){
//        var apiClient= ApiClient.buildApiClient(ApiInterface::class.java)
//        var request =apiClient.registerUser(registerRequest)
//
//        request.enqueue(object:Callback<RegisterResponse>{
//            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
//                if (response.isSuccessful) {
//                    var message = response.body()?.message
//                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
//                    //intent to login
//                } else {
//                    val error = response.errorBody()?.string()
//                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//                }
//            }
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }



    }




