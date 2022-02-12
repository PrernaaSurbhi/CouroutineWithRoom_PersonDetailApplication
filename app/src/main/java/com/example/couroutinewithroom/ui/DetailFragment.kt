package com.example.couroutinewithroom.database

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.couroutinewithroom.PersonListItem
import com.example.couroutinewithroom.PersonviewModelFactory
import com.example.couroutinewithroom.R
import com.example.couroutinewithroom.viewModel.DetailFragViewModel
import com.example.couroutinewithroom.viewModel.DetailFragViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import java.text.NumberFormat
import java.util.*

/**
 * Created by PrernaSurbhi on 12/02/22.
 */
class DetailFragment:DialogFragment(), Toolbar.OnMenuItemClickListener {
    private lateinit var person: Person
    private lateinit var detailFragViewModelFactory: DetailFragViewModelFactory
    private lateinit var detailViewModel: DetailFragViewModel
    private lateinit var personListItem: PersonListItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_Fragment)
        detailFragViewModelFactory = DetailFragViewModelFactory(context?.applicationContext as Application)
        detailViewModel = ViewModelProvider(this,detailFragViewModelFactory).get(DetailFragViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.Theme_Fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsToolbar.setNavigationOnClickListener {
            dismiss()
        }

        // inflate the menu with the star
        detailsToolbar.inflateMenu(R.menu.menu_item)
        detailsToolbar.setOnMenuItemClickListener(this)
        val starMenuItem = detailsToolbar.menu.findItem(R.id.action_favorite)
        val checkbox = starMenuItem.actionView as CheckBox

        arguments?.getParcelable<PersonListItem>(PLAYER_KEY)?.let { personListItem = it }

        detailViewModel.getperson(personListItem).observe(viewLifecycleOwner, Observer {
            this.person = it
            setupFavoriteToggle(checkbox, it)
            displayPlayer()
        })
    }

    private fun displayPlayer() {
        // load the image
        Picasso.get()
            .load(person.imageUrl)
            .error(R.drawable.error_list_image)
            .placeholder(R.drawable.default_list_image)
            .into(playerImage)

        // Load the player info
        textViewPlayerName.text =
            String.format(Locale.getDefault(), "%s %s", person.firstName, person.lastName)
        textViewPlayerDescription.text = person.description
        textViewPlayerCountry.text = person.country
        textViewPlayerRank.text = person.rank.toString()
        textViewPlayerPoints.text = getString(R.string.person_points,
            NumberFormat.getNumberInstance().format(person.points))
        textViewPlayerAgeGender.text =
            getString(R.string.person_age_gender, person.age, person.gender)
    }

    private fun setupFavoriteToggle(checkBox: CheckBox, person: Person) {
        checkBox.setOnCheckedChangeListener { _, b ->
            person.favorite = b
            detailViewModel.update(person)
        }
        checkBox.isChecked = person.favorite
    }

    companion object{
        const val PLAYER_KEY = "player_key"

        fun newInstance(personListItem:PersonListItem): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle().apply {
                putParcelable(PLAYER_KEY, personListItem)
            }
            fragment.arguments = args
            return fragment
        }

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_delete -> {
                deleteCurrentPerson()
                true
            }
            else -> false
        }
    }

    private fun deleteCurrentPerson() {
        detailViewModel.deletePerson(person)
        dismiss()
    }
}