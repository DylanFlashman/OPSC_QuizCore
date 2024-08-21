package com.example.quizcore.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.quizcore.Adapter.QuestionAdapter
import com.example.quizcore.Domain.QuestionModel
import com.example.quizcore.R
import com.example.quizcore.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(), QuestionAdapter.score {

    private lateinit var binding : ActivityQuestionBinding
    var position = 0
    var recievedList : MutableList<QuestionModel> = mutableListOf()
    var allScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        recievedList = intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        binding.apply{
            backBtn.setOnClickListener { finish() }
            progressBar.progress = 1

            questionTxt.text = recievedList[position].question
            val drawableResourceId: Int = binding.root.resources.getIdentifier(
                recievedList[position].picPath,"drawable",binding.root.context.packageName
            )

            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                .into(pic4)

            loadAnswers()


            rightArrow.setOnClickListener {
                if(progressBar.progress == 10){
                    val intent = Intent(this@QuestionActivity,ScoreActivity::class.java)
                    intent.putExtra("Score",allScore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }

                position++
                progressBar.progress = progressBar.progress + 1
                QuestionNumText.text = "Question " + progressBar.progress + "/10"
                questionTxt.text = recievedList[position].question

                val drawableResourceId: Int = binding.root.resources.getIdentifier(
                    recievedList[position].picPath,
                    "drawable", binding.root.context.packageName
                )

                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic4)

                loadAnswers()
            }

            leftArrow.setOnClickListener {
                if(progressBar.progress == 1){

                    return@setOnClickListener
                }

                position--
                progressBar.progress = progressBar.progress - 1
                QuestionNumText.text = "Question " + progressBar.progress + "/10"
                questionTxt.text = recievedList[position].question

                val drawableResourceId: Int = binding.root.resources.getIdentifier(
                    recievedList[position].picPath,
                    "drawable", binding.root.context.packageName
                )

                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic4)

                loadAnswers()
            }
        }
    }

    private fun loadAnswers(){
        val users:MutableList<String> = mutableListOf()
        users.add(recievedList[position].answer_1.toString())
        users.add(recievedList[position].answer_2.toString())
        users.add(recievedList[position].answer_3.toString())
        users.add(recievedList[position].answer_4.toString())

        if(recievedList[position].clickedAnswer!=null) users.add(recievedList[position].clickedAnswer.toString())

        val questionAdapter by lazy{
            QuestionAdapter(recievedList[position].correctAnswer.toString(),users,this)
        }

        questionAdapter.differ.submitList(users)
        binding.QuestionList.apply {
            layoutManager = LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter
        }
    }

    override fun amount(number: Int, clickedAnswer:String){
        allScore += number
        recievedList[position].clickedAnswer = clickedAnswer
    }
}