package com.example.roomapp.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.roomapp.R
import com.example.roomapp.model.User
import com.example.roomapp.viewmodel.LoginViewModel
import com.example.roomapp.viewmodel.LocalDataViewModel

class LoginFragment : Fragment() {

    private lateinit var  login_btn : Button
    private lateinit var registration_btn : Button
    private lateinit var viewModel: LoginViewModel
    private lateinit var mUserViewModel : LocalDataViewModel
    private var users: List<User> = emptyList<User>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.login_fragment, container, false)

        // LoginViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // UserViewModel
        // send userData
        mUserViewModel = ViewModelProvider(this).get(LocalDataViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            viewModel.loadUserData(user)
        })

        login_btn = view.findViewById<Button>(R.id.login_btn)
        login_btn.setOnClickListener(){

            var password = view.findViewById<EditText>(R.id.editTextTextPassword).text.toString()
            var email = view.findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()

            if(viewModel.login(email,password)) {
                findNavController().navigate(R.id.action_loginFragment_to_listFragment)
                Toast.makeText(requireContext(), "Successfully login!", Toast.LENGTH_LONG).show()
            }
        }

        registration_btn = view.findViewById(R.id.registration_btn)
        registration_btn.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        return view
    }
}