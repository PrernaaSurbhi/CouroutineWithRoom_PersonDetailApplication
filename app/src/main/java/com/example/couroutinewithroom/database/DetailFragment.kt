package com.example.couroutinewithroom.database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.couroutinewithroom.PersonListItem
import com.example.couroutinewithroom.R

/**
 * Created by PrernaSurbhi on 12/02/22.
 */
class DetailFragment:DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
}