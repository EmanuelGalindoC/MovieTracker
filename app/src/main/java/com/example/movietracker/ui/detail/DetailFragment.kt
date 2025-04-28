package com.example.movietracker.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movietracker.databinding.FragmentDetailBinding
import com.example.movietracker.ui.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Obtener el argumento movieId con Safe Args
        val movieId = arguments?.let { DetailFragmentArgs.fromBundle(it).movieId }

        movieId?.let {
            viewModel.getMovieDetails(it)
        }

        viewModel.movieDetails.observe(viewLifecycleOwner) { movie ->
            binding.tvTitle.text = movie.title
            binding.tvYear.text = movie.year.toString()
            Glide.with(this).load(movie.imageUrl).into(binding.ivPoster)

            binding.btnFavorite.setOnClickListener {
                viewModel.saveFavorite(movie)
            }

            binding.btnWatched.setOnClickListener {
                viewModel.saveWatched(movie)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
