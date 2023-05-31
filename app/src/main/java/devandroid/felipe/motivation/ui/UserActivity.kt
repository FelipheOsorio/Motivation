package devandroid.felipe.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.appcompat.app.AppCompatActivity
import devandroid.felipe.motivation.infrastructure.MotivationConstants
import devandroid.felipe.motivation.infrastructure.SecurityPreferences
import devandroid.felipe.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.btnSave.isEnabled = false

        verifyName()


        binding.editName.setOnKeyListener { _, i, event ->
            if(i == KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {

                val name = binding.editName.text.toString()
                binding.btnSave.isEnabled = name.isNotEmpty() && name.isNotBlank()
                SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

                return@setOnKeyListener true

            }
            return@setOnKeyListener false
        }


        binding.btnSave.setOnClickListener {
            navegar()
        }
    }

    private fun verifyName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        when {
            name.isNotBlank() && name.isNotEmpty() -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }


    private fun navegar() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}