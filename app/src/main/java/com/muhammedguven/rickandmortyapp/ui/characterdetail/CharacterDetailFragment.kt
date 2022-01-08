package com.muhammedguven.rickandmortyapp.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.muhammedguven.rickandmortyapp.common.StatusManager
import com.muhammedguven.rickandmortyapp.common.extensions.observeNonNull
import com.muhammedguven.rickandmortyapp.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private var _binding: FragmentCharacterDetailBinding? = null

    private val binding get() = _binding!!

    private val args: CharacterDetailFragmentArgs by navArgs()

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchCharacterDetail(args.characterId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        binding.tryAgainButton.setOnClickListener { viewModel.fetchCharacterDetail(args.characterId) }
    }

    private fun setUpViewModel() {

        with(viewModel) {
            getDetailPageViewStateLiveData().observeNonNull(viewLifecycleOwner) {
                renderPageViewState(it)
            }
            getStatusManagerLiveData().observeNonNull(viewLifecycleOwner) {
                renderStatusManager(it)
            }
        }
    }

    private fun renderPageViewState(viewState: CharacterDetailViewState) {
        binding.viewState = viewState
    }

    private fun renderStatusManager(manager: StatusManager) {
        binding.status = manager
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}