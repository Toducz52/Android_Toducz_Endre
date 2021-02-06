package com.example.roomapp.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import com.example.roomapp.model.Restaurant
import com.example.roomapp.model.RestaurantsQuery
import com.example.roomapp.repository.RestaurantsRepository
import com.example.roomapp.viewmodel.MRestaurantsViewModel
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter(var listFragment: com.example.roomapp.fragments.list.ListFragment): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var restaurantList = emptyList<Restaurant>()
    private var mRestaurantsViewModel = MRestaurantsViewModel(RestaurantsRepository())
    private var page : Int = MainActivity.searchParameters.page.toInt();


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
       return  restaurantList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem =  restaurantList[position]


        Log.d("ListAdapter","Position ${position}")
        Log.d("ListAdapter", "List size = ${this.restaurantList.size}")
        Log.d("ListAdapter", "Page = ${this.page}")
        Log.d("ListAdapter", "")
        Log.d("ListAdapter", "")

        holder.itemView.text_view_1.text = currentItem.name
        holder.itemView.text_view_2.text = currentItem.country
        holder.itemView.textView5.text = currentItem.city
        holder.itemView.textView6.text = currentItem.address
        //holder.itemView.image_view.setImageURI(currentItem.image_url)

        Glide.with(holder.itemView.context)
            .load(currentItem.image_url)
            .placeholder((R.drawable.ic_baseline_fastfood_24))
            .apply(RequestOptions().override(holder.itemView.image_view.width, holder.itemView.image_view.height))
            .into(holder.itemView.image_view)


        holder.itemView.relativeLayout.setOnClickListener {
            MainActivity.restaurantsDetail = currentItem
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment()
            holder.itemView.findNavController().navigate(action)
        }

        if(position % (24 * page) == 0 && position != 0){
            Log.d("ListAdapter", "")
            Log.d("ListAdapter", "Query")
            Log.d("ListAdapter", "")
            page++
            MainActivity.searchParameters.page = (page).toString()
            listFragment.queryRestaurantData(MainActivity.searchParameters)
        }
    }

    fun setData(restaurants: List<Restaurant>?){
        Log.d("ListAdapter", "page = ${page}")
        this.restaurantList = restaurants!!
        notifyDataSetChanged()
    }
}

