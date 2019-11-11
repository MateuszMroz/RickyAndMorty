package com.uk.androidrecruitmentapp.ui.base

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.uk.androidrecruitmentapp.executor.AppExecutors


abstract class BaseListAdapter<T, V : ViewDataBinding>(
        appExecutors: AppExecutors,
        diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, BaseViewHolder<V>>(
        AsyncDifferConfig.Builder<T>(diffCallback)
                .setBackgroundThreadExecutor(appExecutors.networkIO())
                .build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val binding = createBinding(parent)
        return BaseViewHolder(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup): V

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun bind(binding: V, item: T?)
}