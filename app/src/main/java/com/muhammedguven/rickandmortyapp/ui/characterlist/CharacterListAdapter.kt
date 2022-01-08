package com.muhammedguven.rickandmortyapp.ui.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muhammedguven.rickandmortyapp.databinding.ItemCharacterCardBinding
import com.muhammedguven.rickandmortyapp.domain.model.Results

class CharacterListAdapter :
    ListAdapter<Results, CharacterListAdapter.CharacterListHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Results) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListHolder {
        val binding = ItemCharacterCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterListHolder(binding, itemClickListener)
    }

    class CharacterListHolder(
        private val binding: ItemCharacterCardBinding,
        private val itemClickListener: (Results) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Results) {
            binding.characterViewState = CharacterItemViewState(character)

            binding.root.setOnClickListener {
                itemClickListener(character)
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterListHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Results, newItem: Results) =
                oldItem == newItem
        }
    }
}


