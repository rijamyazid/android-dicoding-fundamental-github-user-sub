package com.rmyfactory.githubuser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rmyfactory.githubuser.databinding.FragmentFavoriteBinding
import com.rmyfactory.githubuser.datasource.local.model.UserModel
import com.rmyfactory.githubuser.view.adapter.HomeAdapter
import com.rmyfactory.githubuser.view.vm.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteAdapter = HomeAdapter(view) { data ->
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(data)
            )
        }

        with(binding) {
            rvFavorite.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = favoriteAdapter
            }
        }

        viewModel.dataFavoriteUsers.observe(viewLifecycleOwner, {
            favoriteAdapter.setItems(it as ArrayList<UserModel>)
        })

    }

}