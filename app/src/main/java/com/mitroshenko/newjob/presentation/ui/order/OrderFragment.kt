package com.mitroshenko.newjob.presentation.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.presentation.ui.basket.BasketAdapter
import com.mitroshenko.newjob.domain.model.IdCard.IdCardModel
import com.mitroshenko.newjob.databinding.FragmentOrderBinding
import com.mitroshenko.newjob.presentation.ui.basket.BasketViewModel
import com.mitroshenko.newjob.presentation.ui.basket.BasketViewModelFactory

class OrderFragment : Fragment() {
    private val basketViewModel: BasketViewModel by viewModels { BasketViewModelFactory() }
    private val idCardModel: IdCardModel by activityViewModels()
    private val adapter: BasketAdapter by lazy {
        BasketAdapter { entity ->
            idCardModel.idCard.value = entity.idCardProd
            view?.findNavController()!!
                .navigate(R.id.action_orderFragment_to_productCardFragment)
        }
    }
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.apply {
            rcBasket.layoutManager = LinearLayoutManager(context)
            rcBasket.adapter = adapter
            btnArrange.setOnClickListener{
                view?.findNavController()!!
                    .navigate(R.id.action_orderFragment_to_confirmationFragment)

            }
        }
        basketViewModel.allProducts.observe(viewLifecycleOwner) { prod ->
            prod.let { adapter.submitList(it) }
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}