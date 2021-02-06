package com.example.roomapp.fragments.registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.roomapp.R
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.RegistrationViewModel
import kotlinx.android.synthetic.main.fragment_registration.view.*


class RegistrationFragment : Fragment() {


    private  var IMAGE_PICK_CODE = 1000;
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var imageView : ImageView
    private var imgUrl : String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Registration view model
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        var view = inflater.inflate(R.layout.fragment_registration, container, false)
        imageView = view.imageView

        view.button.setOnClickListener(){
//            val intent = Intent()
//            intent.type = "imgage/*"
//            intent.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(
//                Intent.createChooser(intent, "Pick an image"),
//                IMAGE_PICK_CODE
//            )

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)

        }

        view.registrationOK_btn.setOnClickListener(){

            var email = view.editTextTextEmailAddress2.text.toString()
            var name = view.editTextTextPersonName.text.toString()
            var password = view.editTextTextPassword3.text.toString()
            var address = view.r_address.text.toString()
            var phoneNumber = view.editTextPhone.text.toString()

            if(inputCheck(email, name, password, address, phoneNumber, imgUrl)){
                viewModel.registration(User(email, name, password, imgUrl, address, phoneNumber));
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }else{
                Toast.makeText(requireContext(), "Please fill out all fields correct.!", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            Log.d("RegistrationF", "Result load image")

            imgUrl = data?.data.toString()

            Glide.with(this)
                .load(data?.data)
                .apply(RequestOptions().override(imageView.width, imageView.height))
                .into(imageView)
        }
    }

    fun inputCheck(email:String, name:String, password:String, address:String, phoneNumber:String, url:String) : Boolean{

        if(email.length > 0 && name.length > 0 && password.length > 0 && address.length > 0 && phoneNumber.length > 0 && url.length > 0){
            return true
        }
        return false
    }

}