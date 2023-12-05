package com.mdlb.basicapplication.adapters

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mdlb.basicapplication.R
import com.mdlb.basicapplication.databases.DataObject

class RegisterListAdapter(
    private var data: List<DataObject>,
    private var layout: Int,
    private var itemListener: OnItemClickListener
) : RecyclerView.Adapter<RegisterListAdapter.ViewHolder?>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var fullNameText: TextView = itemView.findViewById<View>(R.id.full_name_text) as TextView
        private var birthdateText: TextView = itemView.findViewById<View>(R.id.birthdate_text) as TextView
        private var genderText: TextView = itemView.findViewById<View>(R.id.gender_text) as TextView
        fun bind(data: DataObject, itemListener: OnItemClickListener) {
            fullNameText.text = data.firstName + " " + data.lastName
            birthdateText.text = data.birthDate + " " + data.timeDate
            genderText.text = data.gender

            itemView.setOnClickListener {
                itemListener.onItemClick(data, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        val vh = ViewHolder(view)
        return vh
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], itemListener)
    }

    interface OnItemClickListener {
        fun onItemClick(name: DataObject, position: Int)
    }
}
