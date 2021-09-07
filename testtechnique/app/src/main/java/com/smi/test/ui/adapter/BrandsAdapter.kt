package com.smi.test.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smi.test.data.model.Brands
import com.smi.test.global.listener.DataAdapterListener
import com.smi.test.global.listener.OnItemClickedListener
import com.smi.test.ui.view.BrandsViewHolder
import com.squareup.picasso.Picasso

class BrandsAdapter(private val picasso: Picasso) : RecyclerView.Adapter<BrandsViewHolder>(),
    DataAdapterListener<List<Brands>> {

    private val news = arrayListOf<Brands>();

    lateinit var listener: OnItemClickedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        return BrandsViewHolder.create(parent, listener, picasso)
    }

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun setData(data: List<Brands>) {
        news.clear()
        news.addAll(data)
        notifyDataSetChanged()
    }
}
