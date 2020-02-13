package com.interview.challenge.celouser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.interview.challenge.celouser.view.UserListFragment
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("menu", "onActivity")
        if (menu != null) {
            UserListFragment().onCreateOptionsMenu(menu, menuInflater)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("menu", "OnOptionsActivity")
        if (item != null){
            UserListFragment().onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }*/
}
