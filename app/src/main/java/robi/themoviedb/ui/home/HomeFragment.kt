package robi.themoviedb.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import robi.themoviedb.data.model.Result
import robi.themoviedb.databinding.FragmentHomeBinding
import robi.themoviedb.databinding.ItemCardContentBinding
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

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.actionListener = object : Adapter.OnActionListener{
            override fun onAction(result: Result) {
                val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                findNavController().navigate(direction)
            }
        }
        binding.rcMovieList.adapter = adapter
        adapter.notifyDataSetChanged()
        setupViewModel()
        binding.pullToRefresh.setOnRefreshListener { viewModel.getTrending(1) }
        /*binding.layoutContent.setOnScrollChangeListener(object : OnScrollListener,
            View.OnScrollChangeListener {
            override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {}

            override fun onScroll(p0: AbsListView?, p1: Int, p2: Int, p3: Int) {}

            override fun onScrollChange(p0: View?, p1: Int, p2: Int, p3: Int, p4: Int) {

                Log.e("movieLog", "onScrollChange:: ${binding.layoutContent.getChildAt(0).getHeight()};  $p1, $p2, $p3, $p4")
            }
        })*/
        binding.layoutContent.viewTreeObserver.addOnScrollChangedListener {
            ViewTreeObserver.OnScrollChangedListener {
                if (binding.layoutContent.getChildAt(0).bottom
                    <= (binding.layoutContent.height + binding.layoutContent.scrollY)) {
                    Log.e("movieLog", "At Bottom")
                } else {
                    Log.e("movieLog", "not Bottom")
                }
            }
        }
        binding.floatingActionButton.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(direction)
        }
    }

    private fun setupViewModel() {
        viewModel.movieRepository.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                    adapter.list = listOf(null, null, null, null, null, null, null)
                    adapter.notifyDataSetChanged()
                    binding.pullToRefresh.isRefreshing = false
                }
                is NetworkState.Success -> {
                    adapter.list = it.data?.results!!
                    adapter.notifyDataSetChanged()
                    binding.pullToRefresh.isRefreshing = false
                }
                else -> {
                    binding.layoutContent.visibility = View.GONE
                    binding.layoutEmpty.visibility = View.VISIBLE
                }
            }
        }

        viewModel.getTrending(1)
    }
}