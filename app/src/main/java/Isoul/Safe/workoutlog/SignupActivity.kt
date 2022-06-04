package Isoul.Safe.workoutlog

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
    lateinit var tilFirstName:TextInputLayout
    lateinit var etFirstName:TextInputEditText

    lateinit var tilLastName: TextInputLayout
    lateinit var etLastName: TextInputEditText

    lateinit var tilEmail: TextInputLayout
    lateinit var etSEmail: TextInputEditText

    lateinit var tilPassword:TextInputLayout
    lateinit var etSPassword:TextInputEditText

    lateinit var tilConfirmP:TextInputLayout
    lateinit var etConfirmP:TextInputEditText

    lateinit var btnSignUp: Button
    lateinit var tvSignIn:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        tilFirstName = findViewById(R.id.tilFirstName)
        etFirstName = findViewById(R.id.etFirstName)

        tilLastName = findViewById(R.id.tilLastName)
        etLastName = findViewById(R.id.etLastName)

        tilEmail = findViewById(R.id.tilEmail)
        etSEmail= findViewById(R.id.etSEmail)

        tilPassword = findViewById(R.id.tilPassword)
        etSPassword = findViewById(R.id.etSPassword)

        tilConfirmP = findViewById(R.id.tilConfirmP)
        etConfirmP = findViewById(R.id.etConfirmP)

        btnSignUp = findViewById(R.id.btnSignUp)
        tvSignIn= findViewById(R.id.tvSignIn)

        btnSignUp.setOnClickListener {
            validateSignUp()
        }
        tvSignIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
    private fun validateSignUp(){
        var firstName= etFirstName.text.toString()
        var lastName = etLastName.text.toString()
        var email= etSEmail.text.toString()
        var sPassword= etSPassword .text.toString()
        var confirm= etConfirmP .text.toString()

        if (firstName.isBlank()){
            tilFirstName.setError ("First Name is required")
            return
        }

        if (lastName.isBlank()){
            tilLastName.error ="Last name is required"
        }

        if (email.isBlank()) {
            tilEmail.error = "Email is required"
        }
        if (sPassword.isBlank()) {
            tilPassword.error = "Password is required"
        }
        if (confirm.isBlank()){
            tilConfirmP.error =" Confirmation is required";

        }
        if (confirm==sPassword) {

        } else{
            tilConfirmP.error="Password Invalid"

        }


    }
}



