package com.example.roomapp.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import com.example.roomapp.factory.MRestaurantsViewModelFactory
import com.example.roomapp.model.Restaurant
import com.example.roomapp.model.SearchParameters
import com.example.roomapp.repository.RestaurantsRepository
import com.example.roomapp.viewmodel.MRestaurantsViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var viewModel:  MRestaurantsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter(this)
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // MRestaurantViewModel
        val repository = RestaurantsRepository()
        val viewModelFactory = MRestaurantsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MRestaurantsViewModel::class.java)

        viewModel.myResponseRestaurant.observe(viewLifecycleOwner, Observer { restaurants : List<Restaurant> ->
            adapter.setData(restaurants)
            Log.d("ListFragment", "setData")
        })

        //queryRestaurantData(MainActivity.searchParameters)
        /*
        viewModel.pageMutableLiveData.observe(viewLifecycleOwner, Observer { page ->
            queryRestaurantData(viewModel.searchParameters)
        })*/

        // Igy ertem el hogy csak egyszer hivodjon fel, bar hanyszor is ujroeledjen a Fragmens
        if(viewModel.get){
            queryRestaurantData(MainActivity.searchParameters)
            viewModel.get = false
        }


        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    public fun queryRestaurantData(searchParameters: SearchParameters){
        viewModel.getPostRestaurants(
            searchParameters.price,
            searchParameters.name,
            searchParameters.state,
            searchParameters.city,
            searchParameters.zip,
            searchParameters.country,
            searchParameters.page)
        Log.d("ListFragment", "page")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_person) {
            findNavController().navigate(R.id.action_listFragment_to_profileFragment)
        }
        if (item.itemId == R.id.menu_search) {
            findNavController().navigate(R.id.action_listFragment_to_searchFragment)
        }
        return super.onOptionsItemSelected(item)
    }

}