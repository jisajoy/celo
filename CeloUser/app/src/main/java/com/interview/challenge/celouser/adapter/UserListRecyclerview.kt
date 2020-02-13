package com.interview.challenge.celouser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.interview.challenge.celouser.databinding.ListItemUserBinding
import com.interview.challenge.celouser.model.User
import com.interview.challenge.celouser.model.UserListDatabase

class UserListRecyclerview(val clickListener: CeloClickListener) :
    PagedListAdapter<UserListDatabase, UserListRecyclerview.ViewHolder>(SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserListDatabase?, clickListener: CeloClickListener) {
            binding.list = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class SleepNightDiffCallback : DiffUtil.ItemCallback<UserListDatabase>() {

    override fun areItemsTheSame(oldItem: UserListDatabase, newItem: UserListDatabase): Boolean {
        return oldItem.email == newItem.email
    }


    override fun areContentsTheSame(oldItem: UserListDatabase, newItem: UserListDatabase): Boolean {
        return oldItem == newItem
    }

}

class CeloClickListener(val clickListener: (user: User) -> Unit) {
    fun onClick(userData: UserListDatabase) {
        var user = User(
            userData.cell,
            userData.dob,
            userData.age,
            userData.email,
            userData.gender,
            userData.location,
            userData.login,
            userData.name,
            userData.phone,
            userData.picture_large,
            userData.picture_medium
        )
        clickListener(user)
    }
}
