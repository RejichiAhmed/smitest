package com.smi.test.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.smi.test.R
import com.smi.test.data.model.Brands
import com.smi.test.databinding.ItemBrandsBinding
import com.smi.test.global.listener.OnItemClickedListener
import com.squareup.picasso.Picasso

class BrandsViewHolder(
    private val binding: ItemBrandsBinding,
    private val onItemClickedListener: OnItemClickedListener,
    private val picasso: Picasso
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(brands: Brands) {
        binding.brand = brands
        binding.picasso = picasso
        binding.imageUrl = brands.pic
        binding.onItemClickedListener = onItemClickedListener
        binding.placeHolder = AppCompatResources.getDrawable(binding.root.context, R.mipmap.ic_launcher)
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup, onItemClickedListener: OnItemClickedListener, picasso: Picasso): BrandsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemBrandsBinding.inflate(inflater, parent, false)
            return BrandsViewHolder(
                binding,
                onItemClickedListener,
                picasso
            )
        }
    }
}