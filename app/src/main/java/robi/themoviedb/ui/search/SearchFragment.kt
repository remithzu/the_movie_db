package robi.themoviedb.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import robi.themoviedb.common.Utils.hideKeyboard
import robi.themoviedb.data.model.Result
import robi.themoviedb.databinding.FragmentSearchBinding
import robi.themoviedb.network.NetworkState
import robi.themoviedb.ui.adapter.Adapter
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    @Inject
    lateinit var viewModel: SearchViewModel
    private lateinit var adapter: Adapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var list: MutableList<Result?> = mutableListOf()
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.actionListener = object : Adapter.OnActionListener{
            override fun onAction(result: Result) {
                val direction = SearchFragmentDirections.actionSearchFragmentToDetailFragment(result.id)
                findNavController().navigate(direction)
            }
        }
        binding.rcMovieList.adapter = adapter
        adapter.notifyDataSetChanged()
        setupViewModel()
        binding.btnSearch.setOnClickListener {
            viewModel.getSearch(binding.tieSearch.text.toString(), 1)
            requireActivity().hideKeyboard()
        }

        binding.scrollContent.viewTreeObserver.addOnScrollChangedListener {
            val v = binding.scrollContent.getChildAt(binding.scrollContent.childCount - 1)
            val b = v.bottom - (binding.scrollContent.height + binding.scrollContent.scrollY)
            if (b == 0) {
                page += 1
                loadingShow()
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                    delay(1500)
                    viewModel.getSearch(binding.tieSearch.text.toString(), page)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        viewModel.movieRepository.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                    if (page <= 1){
                        adapter.list = listOf(null, null, null, null, null)
                        adapter.notifyDataSetChanged()
                    }
                }
                is NetworkState.Success -> {
                    binding.layoutContent.visibility = View.VISIBLE
                    binding.layoutEmpty.visibility = View.GONE
                    loadingHide()
                    it.data?.results?.forEach { d ->
                        list.add(d)
                    }
                    adapter.list = list
                    adapter.notifyDataSetChanged()
                }
                else -> {
                    loadingHide()
                    binding.layoutContent.visibility = View.GONE
                    binding.layoutEmpty.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun loadingShow() {
        binding.itemLoading.cardContent.visibility = View.VISIBLE
        binding.itemLoading.shimmer.startShimmer()
    }

    private fun loadingHide() {
        binding.itemLoading.shimmer.stopShimmer()
        binding.itemLoading.cardContent.visibility = View.GONE
    }
}