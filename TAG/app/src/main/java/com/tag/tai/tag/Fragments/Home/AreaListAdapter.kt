package com.tag.tai.tag.Fragments.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filterable
import android.widget.TextView
import com.tag.tai.tag.R
import com.tag.tai.tag.Services.Responses.GetAreas.AreaData

open class AreaListAdapter(val mContext: Context, val areas: List<AreaData>)
    : ArrayAdapter<AreaData>(mContext, R.layout.popup_dropdown_item), Filterable {

    var data: List<AreaData> = areas
    var selectedItemPosition: Int = -1
    val areasFilter by lazy {
        AreasFilter().apply {
            areasAdapter = this@AreaListAdapter
        }
    }

    //checking if empty
    override fun isEmpty(): Boolean {
        if (data.size == 0)
            return true
        return false
    }

    //creating/recycling view
    //when needed
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = null
        if (convertView == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.popup_dropdown_item, parent, false)
            bind(data[position], view, position)
            return view
        }
        bind(data[position], convertView, position)
        return convertView
    }

    //count
    override fun getCount(): Int {
        return data.size
    }

    //setting selected area
    fun setSelectedArea(position: Int) {
        if (selectedItemPosition >= 0) {
            data[selectedItemPosition].isSelected = false
        }
        if (position >= 0) {
            data[position].isSelected = true
        }
        selectedItemPosition = position
    }


    //getting selected area
    fun getSelectedArea(): AreaData? {
        if (selectedItemPosition >= 0)
            return data[selectedItemPosition]
        else
            return null
    }

    //binding data
    fun bind(area: AreaData, itemView: View, position: Int) {
        if (area.isSelected)
            selectedItemPosition = position
        itemView.findViewById<TextView>(R.id.tv_area_name).text = area.ddText
    }

    override fun getFilter(): AreasFilter {
        return areasFilter
    }
}