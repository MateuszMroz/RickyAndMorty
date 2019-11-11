package com.uk.androidrecruitmentapp.ui.locations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.data.locations.Result
import com.uk.androidrecruitmentapp.databinding.LocationsItemBinding
import com.uk.androidrecruitmentapp.executor.AppExecutors
import com.uk.androidrecruitmentapp.ui.base.BaseListAdapter


class LocationsAdapter(
        appExecutors: AppExecutors):
        BaseListAdapter<Result, LocationsItemBinding>(
                appExecutors = appExecutors,
                diffCallback = object: DiffUtil.ItemCallback<Result>() {
                    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                        return oldItem.name == newItem.name
                    }

                    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                        return oldItem.name == newItem.name
                    }
                }
        ){

    override fun createBinding(parent: ViewGroup): LocationsItemBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.locations_item,
                parent,
                false
        )
    }

    override fun bind(binding: LocationsItemBinding, item: Result?) {
        binding.result = item
    }
}