package com.example.roomapp.fragments.profil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import com.example.roomapp.viewmodel.LocalDataViewModel
import com.google.gson.internal.bind.ArrayTypeAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.net.URI


class ProfileFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_profile, container, false)

        var user = MainActivity.loggedUser!!

        view.p_name.text = user.name
        view.p_address.text = user.address
        view.p_phoneNumber.text = user.phoneNumber

        Log.d("ProfilFragment", "${user.imgUrl}")

        Glide.with(this)
            .load(user.imgUrl)
            .apply(RequestOptions().override(view.imageView4.width, view.imageView4.height))
            .into(view.imageView4)


        var viewModel = ViewModelProvider(this).get(LocalDataViewModel::class.java)

        viewModel.readFavoriteRestaurants.observe(viewLifecycleOwner, Observer { list ->

            val nameList:List<String> = list.map { it -> it.name }
            val adapter = activity?.let {
                ArrayAdapter<String>(
                    it,
                    android.R.layout.simple_spinner_item,
                    nameList
                )
            }
            view.p_list.adapter = adapter
        })

        viewModel.readFavorite(MainActivity.loggedUser?.email!!)

        return view
    }

}