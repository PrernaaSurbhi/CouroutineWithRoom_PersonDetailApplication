package com.example.couroutinewithroom

import android.app.Application
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.couroutinewithroom.database.DetailFragment
import com.example.couroutinewithroom.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),PersonAdapter.OnItemClickListener {
    private lateinit var personViewModel: PersonViewModel
    private lateinit var personAdapter: PersonAdapter
    private lateinit var personviewModelFactory: PersonviewModelFactory
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this@MainActivity, RecyclerView
                .VERTICAL)
        )
        personviewModelFactory = PersonviewModelFactory(application)
        personViewModel =
            ViewModelProvider(this, personviewModelFactory).get(PersonViewModel::class.java)
        personViewModel.getallPersonsList().observe(this, Observer<List<PersonListItem>> { personListItem ->
            personAdapter = PersonAdapter(personListItem as MutableList<PersonListItem>,this)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = personAdapter
          //  personAdapter.setData(personListItem)
        })

//        personAdapter = PersonAdapter(mutableListOf())


    }

    override fun onItemClicked(personListItem: PersonListItem) {
        val fragment = DetailFragment.newInstance(personListItem)
        fragment.show(supportFragmentManager, "DetailsFragment")
    }
}