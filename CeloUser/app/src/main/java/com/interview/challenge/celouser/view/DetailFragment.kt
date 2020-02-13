package com.interview.challenge.celouser.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.interview.challenge.celouser.R
import com.interview.challenge.celouser.database.getDatabase
import com.interview.challenge.celouser.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = DetailFragmentArgs.fromBundle(arguments).userDetails
        Log.d("arguments", arguments.cell)
        val viewModelFactory = DetailModelFactory(arguments)

        // Get a reference to the ViewModel associated with this fragment.
        val detailViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(DetailViewModel::class.java)

        binding.userDetails = detailViewModel

        binding.setLifecycleOwner(this)
        return binding.root
    }


}
