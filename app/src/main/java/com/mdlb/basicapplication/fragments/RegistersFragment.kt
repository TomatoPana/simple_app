package com.mdlb.basicapplication.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mdlb.basicapplication.R
import com.mdlb.basicapplication.adapters.RegisterListAdapter
import com.mdlb.basicapplication.databases.DBHelper
import com.mdlb.basicapplication.databases.DataObject

class RegistersFragment : Fragment() {

    private lateinit var listLayout: RecyclerView
    private lateinit var emptyListLayout: ConstraintLayout
    private var databaseConnection: DBHelper? = null
    private lateinit var adapter: RegisterListAdapter
    private lateinit var list: MutableList<DataObject>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registers, container, false)
        databaseConnection = DBHelper.getInstance(this.requireContext())

        listLayout = view.findViewById(R.id.list_layout)
        emptyListLayout = view.findViewById(R.id.empty_list_layout)

        list = (databaseConnection?.getData() as MutableList<DataObject>?)!!

        val layoutManager = LinearLayoutManager(this.context)
        adapter = RegisterListAdapter(list, R.layout.register_view_list, object: RegisterListAdapter.OnItemClickListener {
            override fun onItemClick(name: DataObject, position: Int) {
                val builder = AlertDialog.Builder(this@RegistersFragment.requireContext())
                builder.setTitle("Confirm")
                builder.setMessage("Are you sure to delete this element?")

                builder.setPositiveButton(android.R.string.yes) { _, _ ->
                    deleteName(name, position)
                }

                builder.setNegativeButton(android.R.string.no) { _, _ -> }
                builder.show()
            }
        })

        listLayout.layoutManager = layoutManager
        listLayout.adapter = adapter

        if (databaseConnection?.getData()!!.isNotEmpty()) {
            emptyListLayout.visibility = View.GONE
            listLayout.visibility = View.VISIBLE
        } else {
            listLayout.visibility = View.GONE
            emptyListLayout.visibility = View.VISIBLE
        }

        adapter.notifyDataSetChanged()

        return view
    }

    private fun deleteName(name: DataObject, index: Int) {
        try {
            databaseConnection?.delete(name.id)
            list.removeAt(index)

            adapter.notifyItemRemoved(index)
            adapter.notifyDataSetChanged()

            adapter = RegisterListAdapter(list, R.layout.register_view_list, object: RegisterListAdapter.OnItemClickListener {
                override fun onItemClick(name: DataObject, position: Int) {
                    val builder = AlertDialog.Builder(this@RegistersFragment.requireContext())
                    builder.setTitle("Confirm")
                    builder.setMessage("Are you sure to delete this element?")

                    builder.setPositiveButton(android.R.string.yes) { _, _ ->
                        deleteName(name, position)
                    }

                    builder.setNegativeButton(android.R.string.no) { _, _ -> }
                    builder.show()
                }
            })
            listLayout.adapter = adapter

            if (databaseConnection?.getData()!!.isNotEmpty()) {
                emptyListLayout.visibility = View.GONE
                listLayout.visibility = View.VISIBLE
            } else {
                listLayout.visibility = View.GONE
                emptyListLayout.visibility = View.VISIBLE
            }

        } catch (_: IndexOutOfBoundsException) {
        }
    }

    override fun onResume() {
        databaseConnection = DBHelper.getInstance(this.requireContext())
        list = databaseConnection?.getData()!!.toMutableList()
        adapter = RegisterListAdapter(list, R.layout.register_view_list, object: RegisterListAdapter.OnItemClickListener {
            override fun onItemClick(name: DataObject, position: Int) {
                val builder = AlertDialog.Builder(this@RegistersFragment.requireContext())
                builder.setTitle("Confirm")
                builder.setMessage("Are you sure to delete this element?")

                builder.setPositiveButton(android.R.string.yes) { _, _ ->
                    deleteName(name, position)
                }

                builder.setNegativeButton(android.R.string.no) { _, _ -> }
                builder.show()
            }
        })
        listLayout.adapter = adapter

        if (databaseConnection?.getData()!!.isNotEmpty()) {
            emptyListLayout.visibility = View.GONE
            listLayout.visibility = View.VISIBLE
        } else {
            listLayout.visibility = View.GONE
            emptyListLayout.visibility = View.VISIBLE
        }

        super.onResume()
    }

    override fun onPause() {
        databaseConnection?.close()
        databaseConnection = null
        super.onPause()
    }

    override fun onDestroyView() {
        databaseConnection?.close()
        databaseConnection = null
        super.onDestroyView()
    }
}