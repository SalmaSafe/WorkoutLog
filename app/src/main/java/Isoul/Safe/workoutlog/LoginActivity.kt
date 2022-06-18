package Isoul.Safe.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin: Button
    private lateinit var tilEmail: TextInputLayout
    lateinit var tilPassword: TextInputLayout
    lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var tvSignUp:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp =findViewById(R.id.tvSignUp)

        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etSEmail)

        btnLogin.setOnClickListener {
               validateLogin()
            val intent =Intent( this, HomeActivity::class.java)
            startActivity(intent)

        }
        tvSignUp.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validateLogin() {
        var email = etEmail.text.toString()
        var password = etPassword.text.toString()
        var error =false


        if (email.isBlank()) {
            tilEmail.error = getString(R.string.email_required)
            error=true
        }
        if (password.isBlank()) {
            tilPassword.error = "Password is required"
            error = true
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

    }
}