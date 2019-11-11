package com.uk.androidrecruitmentapp.ui.episodes


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.uk.androidrecruitmentapp.dagger.Injectable

import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.databinding.FragmentEpisodesBinding
import com.uk.androidrecruitmentapp.executor.AppExecutors
import com.uk.androidrecruitmentapp.ui.base.BaseFragment
import com.uk.androidrecruitmentapp.ui.episodes.adapter.EpisodesAdapter
import com.uk.androidrecruitmentapp.util.Status
import com.uk.androidrecruitmentapp.util.autoCleared
import timber.log.Timber

import javax.inject.Inject

class EpisodesFragment : BaseFragment(), Injectable {

    companion object {
        fun newInstance() = EpisodesFragment()
    }

    @Inject
    lateinit var appExecutors: AppExecutors
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var episodesViewModel: EpisodesViewModel

    var binding by autoCleared<FragmentEpisodesBinding>()
    var episodesAdapter by autoCleared<EpisodesAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_episodes,
                container,
                false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        episodesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EpisodesViewModel::class.java)

        binding.setLifecycleOwner(viewLifecycleOwner)
        episodesAdapter = EpisodesAdapter(appExecutors)
        binding.episodesList.adapter = episodesAdapter

        initEpisodesList()
    }

    private fun initEpisodesList() {
        episodesViewModel.result.observe(viewLifecycleOwner, Observer {
            episodesAdapter.submitList(it)
        })

        episodesViewModel.networkState.observe(viewLifecycleOwner, Observer {
            binding.loadEpisodes = it
            if(it.status == Status.ERROR) showError(it.message, binding.root)
        })
    }

}
