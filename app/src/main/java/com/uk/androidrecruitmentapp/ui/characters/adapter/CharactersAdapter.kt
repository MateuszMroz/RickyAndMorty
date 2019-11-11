package com.uk.androidrecruitmentapp.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.data.characters.Result
import com.uk.androidrecruitmentapp.databinding.CharacterItemBinding
import com.uk.androidrecruitmentapp.executor.AppExecutors
import com.uk.androidrecruitmentapp.ui.base.BaseListAdapter


class CharactersAdapter (
        appExecutors: AppExecutors):
        BaseListAdapter<Result, CharacterItemBinding>(
                appExecutors = appExecutors,
                diffCallback = object: DiffUtil.ItemCallback<Result>() {
                    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                        return oldItem.name == newItem.name
                    }

                    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                        return oldItem.name == newItem.name
                    }
                }
        ) {

    override fun createBinding(parent: ViewGroup): CharacterItemBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.character_item,
                parent,
                false
        )
    }

    override fun bind(binding: CharacterItemBinding, item: Result?) {
        binding.result = item
    }
}