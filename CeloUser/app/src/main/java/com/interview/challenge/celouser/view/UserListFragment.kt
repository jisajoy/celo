package com.interview.challenge.celouser.view


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager

import com.interview.challenge.celouser.R
import com.interview.challenge.celouser.adapter.CeloClickListener
import com.interview.challenge.celouser.adapter.UserListRecyclerview
import com.interview.challenge.celouser.databinding.FragmentUserListBinding
import com.interview.challenge.celouser.model.UserListDatabase

/**
 * A simple [Fragment] subclass.
 */
class UserListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentUserListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_list, container, false
        )

        val application = requireNotNull(this.activity).application

        val viewmodelFactory = UserListModelFactory(application)
        val userListViewmodel = ViewModelProviders.of(
            this, viewmodelFactory
        ).get(UserListViewModel::class.java)

        binding.userlistviewmodel = userListViewmodel
        binding.setLifecycleOwner(this)
        val manager = GridLayoutManager(activity, 2)
        binding.userListRv.layoutManager = manager
        // Observer for the network error.
        userListViewmodel.eventNetworkError.observe(
            viewLifecycleOwner,
            Observer<Boolean> { isNetworkError ->
                if (isNetworkError) onNetworkError(userListViewmodel)
            })
/*Adapter click implemented on fragment*/
        val adapter = UserListRecyclerview(CeloClickListener { user ->
            userListViewmodel.onUserCardClicked(user)
        })

        binding.userListRv.adapter = adapter
        /*changes to the userlist submitted to the adapter for updating the recycler view*/
        userListViewmodel.userList.observe(
            viewLifecycleOwner,
            Observer<PagedList<UserListDatabase>> {
                it?.let {
                    adapter.submitList(it)
                }
            })
        userListViewmodel.navigateToDetail.observe(viewLifecycleOwner, Observer { userData ->
            userData?.let {
                Log.d("night", "${userData}")
                this.findNavController().navigate(
                    UserListFragmentDirections.actionUserListFragmentToDetailFragment(userData)
                )
                userListViewmodel.onSleepDetailNavigated()
            }
        })
        //setHasOptionsMenu(true)
        return binding.root
    }

    private fun onNetworkError(userListViewmodel: UserListViewModel) {
        if (!userListViewmodel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            userListViewmodel.onNetworkErrorShown()
        }
    }

}
