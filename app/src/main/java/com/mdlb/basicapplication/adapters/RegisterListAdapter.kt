package com.mdlb.basicapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mdlb.basicapplication.R
import com.mdlb.basicapplication.databases.Register

class RegisterListAdapter : ListAdapter<Register, RegisterListAdapter.RegisterViewHolder>(RegistersComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterViewHolder {
        return RegisterViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RegisterViewHolder, position: Int) {
        val current = getItem(position)
        // holder.bind()
    }

    class RegisterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(androidx.core.R.id.text)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): RegisterViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.register_view_list, parent, false)
                return RegisterViewHolder(view)
            }
        }
    }
}

class RegistersComparator : DiffUtil.ItemCallback<Register>() {
    override fun areItemsTheSame(oldItem: Register, newItem: Register): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Register, newItem: Register): Boolean {
        return oldItem.id == newItem.id
    }
}
