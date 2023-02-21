package robi.themoviedb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import robi.themoviedb.data.model.Result
import robi.themoviedb.databinding.FragmentHomeBinding
import robi.themoviedb.network.NetworkState
import robi.themoviedb.ui.adapter.Adapter
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModel: HomeViewModel
    private lateinit var adapter: Adapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var list: MutableList<Result?> = mutableListOf()
    private var page = 1
    private var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        binding.pullToRefresh.setOnRefreshListener {
            list = mutableListOf()
            page = 1
            viewModel.getTrending(page)
        }

        binding.floatingActionButton.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(direction)
        }

        binding.scrollContent.viewTreeObserver.addOnScrollChangedListener {
            val v = binding.scrollContent.getChildAt(binding.scrollContent.childCount - 1)
            val b = v.bottom - (binding.scrollContent.height + binding.scrollContent.scrollY)
            if (b == 0) {
                if (isLoading) {
                    page += 1
                    loadingShow()
                    isLoading = false
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                        delay(2000)
                        Log.e("movieLog", "page: $page")
                        viewModel.getTrending(page)
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupViewModel() {
        viewModel.movieRepository.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                    if (page == 1){
                        adapter.list = listOf(null, null, null, null, null)
                        adapter.notifyDataSetChanged()
                    }
                    binding.pullToRefresh.isRefreshing = false
                }
                is NetworkState.Success -> {
                    loadingHide()
                    it.data?.results?.forEach { d ->
                        list.add(d)
                    }
                    adapter.list = list
                    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                        delay(2000)
                        isLoading = true
                    }
                    adapter.notifyDataSetChanged()
                    binding.pullToRefresh.isRefreshing = false
                }
                else -> {
                    loadingHide()
                    binding.scrollContent.visibility = View.GONE
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

    override fun onResume() {
        super.onResume()
        list = mutableListOf()
        viewModel.getTrending(page)
    }
}