package Isoul.Safe.workoutlog

import Isoul.Safe.workoutlog.databinding.ActivityLoginBinding
import Isoul.Safe.workoutlog.databinding.ActivitySignupBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

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
    private fun validateSignUp(){
        var firstName= binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var email= binding.etSEmail.text.toString()
        var sPassword= binding.etSPassword .text.toString()
        var confirm= binding.etConfirmP .text.toString()

        if (firstName.isBlank()){
            binding.tilFirstName.error= "First Name is required"
            return
        }

        if (lastName.isBlank()){
            binding.tilLastName.error ="Last name is required"
        }

        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
        }
        if (sPassword.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if (confirm.isBlank()){
            binding.tilConfirmP.error =" Confirmation is required";

        }
        if (confirm==sPassword) {
            return

        }
        else{
            binding.tilConfirmP.error="Password Invalid"

        }


    }
}



