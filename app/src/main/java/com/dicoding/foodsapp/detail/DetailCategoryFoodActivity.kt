package com.dicoding.foodsapp.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.foodsapp.R
import com.dicoding.foodsapp.databinding.ActivityDetailCategoryFoodsBinding
import com.dicoding.foodsmapp.core.domain.model.CategoriesFood
import org.koin.android.viewmodel.ext.android.viewModel

class DetailCategoryFoodActivity : AppCompatActivity() {

    private val detailCategoryFoodViewModel: DetailCategoryFoodViewModel by viewModel()
    private lateinit var binding: ActivityDetailCategoryFoodsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCategoryFoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailTourism = intent.getParcelableExtra<CategoriesFood>(EXTRA_DATA)
        showDetailTourism(detailTourism)
    }

    private fun showDetailTourism(detailTourism: CategoriesFood?) {
        detailTourism?.let {
            supportActionBar?.title = detailTourism.strCategory
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            binding.content.tvDetailDescription.text = detailTourism.strCategoryDescription
            Glide.with(this@DetailCategoryFoodActivity)
                .load(detailTourism.strCategoryThumb)
                .into(binding.ivDetailImage)

            var statusFavorite = detailTourism.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailCategoryFoodViewModel.setFavoriteTourism(detailTourism, statusFavorite)
                setStatusFavorite(statusFavorite)
                messageFavorite(statusFavorite)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    private fun messageFavorite(isFavorite: Boolean) {
        Toast.makeText(this@DetailCategoryFoodActivity, if (isFavorite) "Added to favorite" else "Removed to favorite", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
