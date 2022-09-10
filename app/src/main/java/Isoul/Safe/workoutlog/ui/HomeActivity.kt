package Isoul.Safe.workoutlog.ui

import Isoul.Safe.workoutlog.R
import Isoul.Safe.workoutlog.databinding.ActivityHomeBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import retrofit2.http.Tag

class HomeActivity : AppCompatActivity() {
    private companion object{
        private const val Tag ="HomeActivity"
    }
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castviews()
        setupBottomNav()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miLogout) {
            Log.i(Tag, "Logout")
            //Log out the user

            val logoutIntent= Intent(this,LoginActivity::class.java)
            logoutIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(logoutIntent)

        }
        return super.onOptionsItemSelected(item)
    }

    fun castviews() {
        binding.fcvHome
        binding.bnvHome
    }

    fun setupBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    val transaction = supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, PlanFragment()).commit()
                    true

                }
                R.id.track -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, TrackFragment()).commit()
                    true
                }

                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}
