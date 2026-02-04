package com.mitroshenko.newjob.presentation.ui.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.data.database.order.OrderEntity
import com.mitroshenko.newjob.databinding.ProductsRcviewBinding

class OrderAdapter(private val onClick: (OrderEntity) -> Unit): ListAdapter<OrderEntity, OrderAdapter.Holder>(Comparator()){
    class Holder(view: View, val onClick: (OrderEntity) -> Unit): RecyclerView.ViewHolder(view){
        private val binding = ProductsRcviewBinding.bind(view)
        fun bind(orderEntity: OrderEntity) = with(binding) {
            itemView.setOnClickListener { onClick(orderEntity) }
            Glide.with(binding.root.context)
                .load(orderEntity.images)
                .into(ivProduct)
        }
        companion object {
            fun create(parent: ViewGroup, onClick: (OrderEntity) -> Unit): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.products_rcview, parent, false)
                return Holder(view, onClick)
            }
        }
    }
    class Comparator: DiffUtil.ItemCallback<OrderEntity>(){
        override fun areItemsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_rcview, parent,false)
        return Holder(view, onClick)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}