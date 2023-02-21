package robi.themoviedb.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import robi.themoviedb.R
import robi.themoviedb.common.Utils.toFormat
import robi.themoviedb.data.model.ActorResponse
import robi.themoviedb.data.model.MovieDetailResponse
import robi.themoviedb.data.model.Result
import robi.themoviedb.databinding.FragmentDetailBinding
import robi.themoviedb.databinding.ItemActorBinding
import robi.themoviedb.databinding.ItemProductionBinding
import robi.themoviedb.network.NetworkState
import robi.themoviedb.ui.dialog.AlertButtonSheet
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment : Fragment() {
    @Inject
    lateinit var viewModel: DetailViewModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter
    private lateinit var adapter2: Adapter2
    private var movieId = 0
    private var trailerUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter()
        adapter2 = Adapter2()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = arguments?.getInt("movie_id")?: 0
        adapter.actionListener = object: Adapter.OnActionListener {
            override fun onAction(result: Result) {
                TODO("Not yet implemented")
            }
        }
        binding.rvActorList.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.rvProductionList.adapter = adapter2
        adapter2.notifyDataSetChanged()
        setupViewModel()
        binding.tvPlayTrailer.setOnClickListener {
            if (trailerUrl!=null) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(trailerUrl)
                    )
                )
            } else {
                val modalBottomSheet = AlertButtonSheet()
                modalBottomSheet.title = "Ops..."
                modalBottomSheet.description = "Trailer not found!"
                modalBottomSheet.background = R.color.red
                modalBottomSheet.show(childFragmentManager, "LocationBottomSheet")
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun setupViewModel() {
        viewModel.movieDetailRepository.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                }
                is NetworkState.Success -> {
                    val d = it.data
                    if (d!=null) {
                        binding.apply {
                            Glide.with(requireContext())
                                .asDrawable()
                                .load("https://image.tmdb.org/t/p/w500${d.posterPath}")
                                .into(ivCover)
                            Glide.with(requireContext())
                                .asDrawable()
                                .load("https://image.tmdb.org/t/p/w500${d.backdropPath}")
                                .into(ivCoverOverlay)
                            tvUserScore.text = "${d.voteAverage.toFormat()}/10"
                            tvTitle.text = d.title
                            tvReleaseStatus.text = d.status
                            tvReleaseDate.text = d.releaseDate
                            tvGenre.text = d.genres.joinToString { g -> g.name }
                            tvTagLine.text = d.tagline
                            tvDescription.text = d.overview
                            adapter2.list = d.productionCompanies
                            adapter2.notifyDataSetChanged()
                        }
                    }
                }
                else -> {
                }
            }
        }

        viewModel.movieActorRepository.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                }
                is NetworkState.Success -> {
                    val d = it.data
                    if (d!=null) {
                        adapter.list = d.cast
                        adapter.notifyDataSetChanged()
                    }
                }
                else -> {
                }
            }
        }

        viewModel.movieTrailerRepository.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkState.Loading -> {
                }
                is NetworkState.Success -> {
                    if (it.data!=null) {
                        trailerUrl = it.data
                    }
                }
                else -> {
                }
            }
        }

        viewModel.getDetailMovie(movieId)
        viewModel.getActor(movieId)
        viewModel.getTrailer(movieId)
    }

    class ViewHolder(val bindItem: ItemActorBinding): RecyclerView.ViewHolder(bindItem.root)
    class Adapter: RecyclerView.Adapter<ViewHolder>() {
        var list = listOf<ActorResponse.Cast?>()
        var actionListener: OnActionListener? = null

        interface OnActionListener {
            fun onAction(result: Result)
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return ViewHolder(
                ItemActorBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) {
            val m = list[position]!!
            Glide.with(holder.itemView.context)
                .asDrawable()
                .load("https://image.tmdb.org/t/p/w500${m.profilePath}")
                .placeholder(ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_box))
                .into(holder.bindItem.ivCover)
            holder.bindItem.tvCharacter.text = m.character
            holder.bindItem.tvName.text = m.originalName
        }
    }

    class ViewHolder2(val bindItem: ItemProductionBinding): RecyclerView.ViewHolder(bindItem.root)
    class Adapter2: RecyclerView.Adapter<ViewHolder2>() {
        var list = listOf<MovieDetailResponse.ProductionCompany?>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder2 {
            return ViewHolder2(
                ItemProductionBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(
            holder: ViewHolder2,
            position: Int
        ) {
            val m = list[position]!!
            holder.bindItem.tvTitle.text = m.name
            holder.bindItem.tvSubtitle.text = m.originCountry
        }
    }
}