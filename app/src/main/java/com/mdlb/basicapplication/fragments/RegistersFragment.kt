package com.mdlb.basicapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mdlb.basicapplication.R

class RegistersFragment : Fragment() {

    private lateinit var listLayout: RecyclerView
    private lateinit var emptyListLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registers, container, false)
        listLayout = view.findViewById(R.id.list_layout)
        emptyListLayout = view.findViewById(R.id.empty_list_layout)
        return view
    }
}