package com.example.quizcore.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizcore.R
import com.example.quizcore.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    var score:Int = 0
    lateinit var binding:ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        score = intent.getIntExtra("Score",0)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply {
            scoreTV.text = score.toString()
            backBtn.setOnClickListener {
                startActivity(
                    Intent(
                    this@ScoreActivity, MainActivity::class.java
                )
                )
                finish()
            }
        }

    }
}