package com.example.starwars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class Adapter: RecyclerView.Adapter<Adapter.ResultViewHolder>() {

    inner class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object: DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {

        val Result = differ.currentList[position]

        holder.itemView.apply {
            val birthYear = findViewById<TextView>(R.id.birth_year)
            birthYear.text = Result.birth_year

            val height = findViewById<TextView>(R.id.height)
            height.text = Result.height

            val gender = findViewById<TextView>(R.id.gender)
            gender.text = Result.gender
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}