package com.uk.androidrecruitmentapp.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar

import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.dagger.Injectable
import com.uk.androidrecruitmentapp.databinding.FragmentLocationsBinding
import com.uk.androidrecruitmentapp.executor.AppExecutors
import com.uk.androidrecruitmentapp.ui.base.BaseFragment
import com.uk.androidrecruitmentapp.ui.locations.adapter.LocationsAdapter
import com.uk.androidrecruitmentapp.util.Status
import com.uk.androidrecruitmentapp.util.autoCleared
import javax.inject.Inject

class LocationsFragment : BaseFragment(), Injectable {

    companion object {
        fun newInstance() = LocationsFragment()
    }

    @Inject
    lateinit var appExecutors: AppExecutors
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var locationViewModel: LocationViewModel

    var binding by autoCleared<FragmentLocationsBinding>()
    var locationAdapter by autoCleared<LocationsAdapter>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_locations,
                container,
                false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        locationViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(LocationViewModel::class.java)

        binding.setLifecycleOwner(viewLifecycleOwner)
        locationAdapter = LocationsAdapter(appExecutors)
        binding.locationsList.adapter = locationAdapter

        initEpisodesList()
    }

    private fun initEpisodesList() {
        locationViewModel.result.observe(viewLifecycleOwner, Observer {
            locationAdapter.submitList(it)
        })

        locationViewModel.networkState.observe(viewLifecycleOwner, Observer {
            binding.loadLocations = it
            if(it.status == Status.ERROR) showError(it.message, binding.root)
        })
    }
}
