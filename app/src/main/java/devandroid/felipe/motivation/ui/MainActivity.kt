package devandroid.felipe.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import devandroid.felipe.motivation.R
import devandroid.felipe.motivation.data.Mock
import devandroid.felipe.motivation.infrastructure.MotivationConstants
import devandroid.felipe.motivation.infrastructure.SecurityPreferences
import devandroid.felipe.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var category = MotivationConstants.Category.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        handleFilter(R.id.img_inclusive)
        handleNextPhrase()

        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textTitle.text = "Ola $name"


        binding.btnNewPhrase.setOnClickListener {
            handleNextPhrase()
        }

        binding.imgInclusive.setOnClickListener(this)
        binding.imgEmoji.setOnClickListener(this)
        binding.imgSun.setOnClickListener(this)

        binding.btnNewName.setOnClickListener {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, "")
            startActivity(Intent(this, UserActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        if(view.id in listOf(R.id.img_inclusive, R.id.img_emoji, R.id.img_sun)) {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(category)
        binding.textPhrase.text = phrase
    }

    private fun handleFilter(id: Int) {
        binding.imgInclusive.setColorFilter(getColor(R.color.dark_purple))
        binding.imgEmoji.setColorFilter(getColor(R.color.dark_purple))
        binding.imgSun.setColorFilter(getColor(R.color.dark_purple))

        when(id){
            R.id.img_inclusive -> {
                binding.imgInclusive.setColorFilter(getColor(R.color.white))
                category = MotivationConstants.Category.ALL
            }
            R.id.img_emoji -> {
                binding.imgEmoji.setColorFilter(getColor(R.color.white))
                category = MotivationConstants.Category.EMOJI
            }
            R.id.img_sun -> {
                binding.imgSun.setColorFilter(getColor(R.color.white))
                category = MotivationConstants.Category.SUN
            }
        }
    }
}