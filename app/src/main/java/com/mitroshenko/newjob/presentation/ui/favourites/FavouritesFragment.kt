package com.mitroshenko.newjob.presentation.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.domain.model.IdCard.IdCardModel
import com.mitroshenko.newjob.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment() {
    private val viewModel: FavouriteViewModel by viewModels { FavouriteViewModelFactory() }
    private val idCardModel: IdCardModel by activityViewModels()
    private val adapter: FavouriteAdapter by lazy {
        FavouriteAdapter { entity ->
            idCardModel.idCard.value = entity.idCardProd
            view?.findNavController()!!
                .navigate(R.id.action_navigation_favourites_to_productCardFragment)
        }
    }
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rcFavourite.layoutManager = LinearLayoutManager(context)
        binding.rcFavourite.adapter = adapter
        viewModel.allFavourite.observe(viewLifecycleOwner) { fav ->
            fav.let { adapter.submitList(it) }
        }
        viewModel.apply {
            val simpleCallback =
                object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return true
                    }
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        when (direction) {
                            ItemTouchHelper.RIGHT -> {
                                delete(position)
                            }
                            ItemTouchHelper.LEFT -> {
                                delete(position)
                            }
                        }
                    }
                }
            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(binding.rcFavourite)
            allFavourite.observe(viewLifecycleOwner) { fav ->
                fav.let { adapter.submitList(it) }
            }
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}