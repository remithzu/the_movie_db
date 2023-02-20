package robi.themoviedb.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import robi.themoviedb.data.model.Result
import robi.themoviedb.databinding.FragmentSearchBinding
import robi.themoviedb.network.NetworkState
import robi.themoviedb.ui.adapter.Adapter
import robi.themoviedb.ui.home.HomeFragmentDirections
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    @Inject
    lateinit var viewModel: SearchViewModel
    private lateinit var adapter: Adapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.actionListener = object : Adapter.OnActionListener{
            override fun onAction(result: Result) {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(result.id)
                findNavController().navigate(direction)
            }
        }
        binding.rcMovieList.adapter = adapter
        adapter.notifyDataSetChanged()
        setupViewModel()
        binding.btnSearch.setOnClickListener {
            viewModel.getSearch(binding.tieSearch.text.toString(), 1)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        viewModel.movieRepository.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                    adapter.list = listOf(null, null, null, null, null, null, null)
                    adapter.notifyDataSetChanged()
                }
                is NetworkState.Success -> {
                    binding.layoutContent.visibility = View.VISIBLE
                    binding.layoutEmpty.visibility = View.GONE
                    adapter.list = it.data?.results!!
                    adapter.notifyDataSetChanged()
                }
                else -> {
                    binding.layoutContent.visibility = View.GONE
                    binding.layoutEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

}