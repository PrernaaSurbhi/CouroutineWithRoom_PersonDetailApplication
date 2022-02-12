package com.example.couroutinewithroom

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by PrernaSurbhi on 10/02/22.
 */
class PersonAdapter(private val personList:MutableList<PersonListItem>,private val listener:OnItemClickListener)
    :RecyclerView.Adapter<PersonAdapter.PersonAdapterViewHolder>() {

    fun setData(players:List<PersonListItem>){
        this.personList.clear()
        this.personList.addAll(players)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item,parent,false)
        return PersonAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonAdapterViewHolder, position: Int) {
        val person:PersonListItem = personList[position]
        holder.personName.text =  String.format(Locale.getDefault(), "%s %s", person.firstName,person.lastName)
        holder.personCountry.text = person.country

        Picasso.get()
            .load(person.imageUrl)
            .error(R.drawable.error_list_image)
            .placeholder(R.drawable.default_list_image)
            .into(holder.personImage)

        val resourceId = if(person.favorite){
            R.drawable.ic_star
        }else{
            R.drawable.ic_star_border
        }

        holder.favPerson.setImageResource(resourceId)
        holder.bind(person,listener)
    }

    override fun getItemCount(): Int {
       return personList.size
    }

    class PersonAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val personImage: ImageView = itemView.findViewById(R.id.personImage)
        val personName: TextView = itemView.findViewById(R.id.person_name_tv)
        val personCountry: TextView = itemView.findViewById(R.id.person_country_tv)
        val favPerson: ImageView = itemView.findViewById(R.id.fav_person)

        fun bind(personListItem:PersonListItem,listener:OnItemClickListener){
            itemView.setOnClickListener {
               listener.onItemClicked(personListItem)
               Log.d("list0001","list item selected")
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(personListItem:PersonListItem)
    }
}