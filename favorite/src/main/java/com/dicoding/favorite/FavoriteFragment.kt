package com.dicoding.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.favorite.databinding.FragmentFavoriteBinding
import com.dicoding.favorite.di.favoriteModule
import com.dicoding.foodsapp.detail.DetailCategoryFoodActivity
import com.dicoding.foodsmapp.core.ui.FoodAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        if (activity != null) {
            val tourismAdapter = FoodAdapter()
            tourismAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailCategoryFoodActivity::class.java)
                intent.putExtra(DetailCategoryFoodActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteTourism.observe(viewLifecycleOwner) { dataTourism ->
                tourismAdapter.setData(dataTourism)
                binding?.viewEmpty?.root?.visibility =
                    if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding?.rvTourism) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = tourismAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
