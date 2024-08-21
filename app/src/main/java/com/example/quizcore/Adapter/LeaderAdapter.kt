package com.example.quizcore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizcore.Domain.UserModel
import com.example.quizcore.databinding.ViewholderLeaderBinding

class LeaderAdapter: RecyclerView.Adapter<LeaderAdapter.ViewHolder>() {

    private lateinit var binding:ViewholderLeaderBinding

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ViewholderLeaderBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: LeaderAdapter.ViewHolder, position: Int) {
        val binding = ViewholderLeaderBinding.bind(holder.itemView)
        binding.titleTv.text = differ.currentList[position].name

        val drawableResourceId: Int = binding.root.resources.getIdentifier(
            differ.currentList[position].pic,"drawable",binding.root.context.packageName
        )

        Glide.with(binding.root.context).load(drawableResourceId).into(binding.pic)

        binding.tvRow.text = (position + 4).toString()

        binding.scoreTxt.text = differ.currentList[position].score.toString()
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallBack=object : DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)

}