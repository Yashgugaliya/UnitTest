package com.example.unittest.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unittest.databinding.ItemQuotesBinding
import com.example.unittest.model.QuotesModelItem

class QuotesAdaptor(private val list: ArrayList<QuotesModelItem>) : RecyclerView.Adapter<QuotesAdaptor.ViewHolder>(){

    class ViewHolder(view: ItemQuotesBinding): RecyclerView.ViewHolder(view.root){
        val text =view.tvQuote
        val autor =view.tvAutor

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemQuotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = list[position].q
        holder.autor.text = list[position].a
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}