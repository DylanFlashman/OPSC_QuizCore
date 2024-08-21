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
import com.example.quizcore.Adapter.LeaderAdapter
import com.example.quizcore.Domain.UserModel
import com.example.quizcore.R
import com.example.quizcore.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {

    lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy {LeaderAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply {
            tvScore.text = loadData().get(0).score.toString()

            tvTitleTop1.text = loadData().get(0).name

            val drawableResourceId1: Int = binding.root.resources.getIdentifier(
                loadData().get(0).pic,"drawable",binding.root.context.packageName
            )

            //add for pic 2 and 3

            Glide.with(root.context)
                .load(drawableResourceId1)
                .into(pic1)

            bottomMenu.setItemSelected(R.id.Board)
            bottomMenu.setOnItemSelectedListener {
                if(it==R.id.home){
                    startActivity(Intent(this@LeaderActivity,MainActivity::class.java))
                }
            }

            var list : MutableList<UserModel> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)
            leaderAdapter.differ.submitList(list)
            leaderView.apply{
                layoutManager = LinearLayoutManager(this@LeaderActivity)
                adapter = leaderAdapter
            }


        }
    }

    private fun loadData():MutableList<UserModel>{
        val users: MutableList<UserModel> = mutableListOf()
        users.add(UserModel(1,"Sophia","person1",4850))
        users.add(UserModel(2,"Daniel","person2",4850))
        users.add(UserModel(3,"James","person3",4850))
        users.add(UserModel(4,"John","person4",4850))
        users.add(UserModel(5,"Emily","person5",4850))
        users.add(UserModel(6,"David","person6",4850))
        users.add(UserModel(7,"Sarah Wilson","person7",4850))
        users.add(UserModel(8,"Micheal","person8",4850))
        users.add(UserModel(9,"Sarah Wilson","person9",4850))
        users.add(UserModel(10,"Sarah Wilson","person9",4850))
        return users
    }

}