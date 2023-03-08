package com.example.countrydisplay.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countrydisplay.R
import com.example.countrydisplay.model.CountryModel

class CountryDisplayAdapter(private val countryList: MutableList<CountryModel>) :
    RecyclerView.Adapter<CountryDisplayAdapter.CountryViewHolder>() {

    class CountryViewHolder(itemView: View) : ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.country_name)
        val countryCode: TextView = itemView.findViewById(R.id.country_code)
        val countryCapital: TextView = itemView.findViewById(R.id.country_capital)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryName.text = buildString {
            append(countryList[position].name)
            append(",")
            append(countryList[position].region)
        }
        holder.countryCode.text = countryList[position].code
        holder.countryCapital.text = countryList[position].capital
    }

    override fun getItemCount(): Int = countryList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<CountryModel>) {
        countryList.clear()
        countryList.addAll(data)
        notifyDataSetChanged()
    }
}
