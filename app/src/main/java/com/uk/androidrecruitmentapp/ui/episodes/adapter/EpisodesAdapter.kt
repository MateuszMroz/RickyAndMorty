package com.uk.androidrecruitmentapp.ui.episodes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.data.episode.Result
import com.uk.androidrecruitmentapp.databinding.EpisodeItemBinding
import com.uk.androidrecruitmentapp.executor.AppExecutors
import com.uk.androidrecruitmentapp.ui.base.BaseListAdapter

class EpisodesAdapter(
        appExecutors: AppExecutors ):
        BaseListAdapter<Result, EpisodeItemBinding>(
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

    override fun createBinding(parent: ViewGroup): EpisodeItemBinding {
        return DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.episode_item,
                parent,
                false
        )
    }

    override fun bind(binding: EpisodeItemBinding, item: Result?) {
        binding.result = item
    }
}