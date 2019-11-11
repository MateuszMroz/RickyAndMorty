package com.uk.androidrecruitmentapp.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.dagger.Injectable
import com.uk.androidrecruitmentapp.databinding.FragmentCharactersBinding
import com.uk.androidrecruitmentapp.executor.AppExecutors
import com.uk.androidrecruitmentapp.ui.base.BaseFragment
import com.uk.androidrecruitmentapp.ui.characters.adapter.CharactersAdapter
import com.uk.androidrecruitmentapp.util.Status
import com.uk.androidrecruitmentapp.util.autoCleared
import javax.inject.Inject

class CharactersFragment : BaseFragment(), Injectable {

    companion object {
        fun newInstance() = CharactersFragment()
    }

    @Inject
    lateinit var appExecutors: AppExecutors
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var charactersViewModel: CharactersViewModel

    var binding by autoCleared<FragmentCharactersBinding>()
    var charactersAdapter by autoCleared<CharactersAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_characters,
                container,
                false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        charactersViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CharactersViewModel::class.java)

        binding.setLifecycleOwner(viewLifecycleOwner)
        charactersAdapter = CharactersAdapter(appExecutors)
        binding.charactersList.adapter = charactersAdapter

        initEpisodesList()
    }

    private fun initEpisodesList() {
        charactersViewModel.result.observe(viewLifecycleOwner, Observer {
            charactersAdapter.submitList(it)
        })

        charactersViewModel.networkState.observe(viewLifecycleOwner, Observer {
            binding.loadCharacters = it
            if (it.status == Status.ERROR) showError(it.message, binding.root)
        })
    }
}
