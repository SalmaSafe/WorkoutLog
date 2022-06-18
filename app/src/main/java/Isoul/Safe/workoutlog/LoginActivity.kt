package Isoul.Safe.workoutlog

import Isoul.Safe.workoutlog.databinding.ActivityHomeBinding
import Isoul.Safe.workoutlog.databinding.ActivityLoginBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
               validateLogin()
            val intent =Intent( this, HomeActivity::class.java)
            startActivity(intent)
            finish()

        }
        binding.tvSignUp.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etSPassword.text.toString()
        var error =false


        if (email.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_required)
            error=true
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }


    }
}