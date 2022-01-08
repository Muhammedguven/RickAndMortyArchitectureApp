package com.muhammedguven.rickandmortyapp.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.muhammedguven.rickandmortyapp.common.GridItemDecoration
import com.muhammedguven.rickandmortyapp.common.StatusManager
import com.muhammedguven.rickandmortyapp.common.extensions.observeNonNull
import com.muhammedguven.rickandmortyapp.databinding.FragmentCharacterListBinding
import com.muhammedguven.rickandmortyapp.domain.model.Results
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: CharacterListViewModel by activityViewModels()

    lateinit var characterListAdapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    private fun setUpViewModel() {

        with(listViewModel) {
            getPageViewStateLiveData().observeNonNull(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            getStatusManagerLiveData().observeNonNull(viewLifecycleOwner) {
                renderStatusManager(it)
            }
            initializeViewModel()
        }
    }

    private fun setUpView() {
        with(binding.recyclerViewCharacterList) {
            apply {
                characterListAdapter = CharacterListAdapter()
                adapter = characterListAdapter.apply {
                    itemClickListener = ::navigateCharacterDetailFragment
                }
                addItemDecoration(GridItemDecoration())
            }
        }
        binding.tryAgainButton.setOnClickListener { listViewModel.initializeViewModel() }
    }

    private fun renderPageViewState(viewState: CharacterListViewState) {
        binding.characterViewState = viewState
        characterListAdapter.submitList(viewState.getCharacterResults())
    }

    private fun renderStatusManager(manager: StatusManager) {
        binding.status = manager
    }

    private fun navigateCharacterDetailFragment(result: Results) {
        findNavController().navigate(CharacterListFragmentDirections.openCharacterDetail(result.id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}