package com.example.roomapp.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_search, container, false)

        var ui_data = MainActivity.searchParameters


        view.s_price.setText(ui_data.price)
        view.s_name.setText(ui_data.name)
        view.s_state.setText(ui_data.state)
        view.s_city.setText(ui_data.city)
        view.s_zip.setText(ui_data.zip)
        view.s_country.setText(ui_data.country)


        view.s_btn.setOnClickListener {
            ui_data.price = view.s_price.text.toString()
            ui_data.name = view.s_name.text.toString()
            ui_data.state = view.s_state.text.toString()
            ui_data.city = view.s_city.text.toString()
            ui_data.zip = view.s_zip.text.toString()
            ui_data.country = view.s_country.text.toString()
            ui_data.page = "1"

            findNavController().navigate(R.id.action_searchFragment_to_listFragment)
        }

        return view
    }
}