package com.example.roomapp.fragments.detail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import com.example.roomapp.model.FavoriteRestaurants
import com.example.roomapp.model.Restaurant
import com.example.roomapp.viewmodel.LocalDataViewModel
import kotlinx.android.synthetic.main.fragment_detaile.view.*
import java.lang.Exception


class DetailFragment : Fragment() {

    private lateinit var mLocalDataViewModel: LocalDataViewModel
    private var isFavorite = false
    private val tempFavoriteRestaurant = FavoriteRestaurants(MainActivity.loggedUser?.email!!, MainActivity.restaurantsDetail?.id.toString(), MainActivity.restaurantsDetail?.name!!)
    private lateinit var menu : Menu


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detaile, container, false)

        val restaurant : Restaurant? = MainActivity.restaurantsDetail

        mLocalDataViewModel = ViewModelProvider(this).get(LocalDataViewModel::class.java)

        view.d_name.text = "Name:  ${restaurant?.name}"
        view.d_address.text = "Address:  ${restaurant?.address}"
        view.d_state.setText("State:  ${restaurant?.address}")
        view.d_city.setText("City:  ${restaurant?.city}")
        view.d_country.setText("Country:  ${restaurant?.country}")
        view.d_phone.setText("Phone:  ${restaurant?.phone}")
        view.d_price.setText("Price:  ${restaurant?.price.toString()}")
        view.d_area.setText("Area:  ${restaurant?.area}")
        view.d_postal_code.setText("Postal_code:  ${restaurant?.postal_code}")


        view.d_Img_btn.setOnClickListener {
            var action = DetailFragmentDirections.actionDetaileFragmentToDetaileImg2(restaurant!!)
            findNavController().navigate(action)
        }


        mLocalDataViewModel.readFavoriteRestaurants.observe(viewLifecycleOwner, Observer{ listRestaurantP ->
            isFavorite = listRestaurantP.contains(tempFavoriteRestaurant)
            Log.d("DetailFragment", "observer --> ${listRestaurantP}")

                try {
                    if(isFavorite){
                        checkNotNull(menu).findItem(R.id.menu_favorite)?.setIcon(R.drawable.ic_baseline_favorite_24)
                    }else{
                        checkNotNull(menu).findItem(R.id.menu_favorite)?.setIcon(R.drawable.ic_baseline_favorite_border_24)
                    }
                }catch (e:Exception){
                    // nem tudom
                }
        })

        Log.d("Query DetailFragment", "${MainActivity.loggedUser?.name!!}")
        mLocalDataViewModel.readFavorite(MainActivity.loggedUser?.email!!)

        view.d_map_btn.setOnClickListener {
            findNavController().navigate(R.id.action_DetaileFragment_to_mapsFragment)
        }


        // Add menu
        setHasOptionsMenu(true)

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) {
            Log.d("Detail", "${item.title}")
            if(isFavorite){
                Log.d("DetailFragment", "Delete")
                mLocalDataViewModel.deleteFavoriteRestaurant(tempFavoriteRestaurant)
                item.setIcon(R.drawable.ic_baseline_favorite_border_24)
                isFavorite = false
            }else {
                isFavorite = true
                mLocalDataViewModel.addFavoriteRestaurant(tempFavoriteRestaurant)
                item.setIcon(R.drawable.ic_baseline_favorite_24)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}