package com.payback.pixabayapplication.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.payback.data.models.HitDataSource
import com.payback.pixabayapplication.presentation.view.adapter.SearchAdapter
import com.payback.pixabayapp.presentation.viewmodel.MainViewModel
import com.payback.pixabayapplication.R
import com.payback.pixabayapplication.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val TAG = "SearchFragment"

    private lateinit var binding: FragmentSearchBinding

    private val compositeDisposable = CompositeDisposable()

    private lateinit var dataSource: HitDataSource

    private lateinit var adapter: SearchAdapter

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        actionListener(view)
        setObservers()
    }



    private fun setupViews() {
        adapter = SearchAdapter( mutableListOf(), requireContext(), itemListener)
        binding.searchResultsRecyclerview.adapter = adapter
    }


    private fun actionListener(view: View) {
        binding.imageView44.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_searchFragment_to_imageListFragment)
        }
    }


    private fun setObservers() {
        viewModel.getQuery()

        viewModel.showProgressBar.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.queryData.observe(viewLifecycleOwner, Observer { newValue ->
            val processedQuery = viewModel.processQuery(newValue)
            viewModel.getHitList(processedQuery)
        })

        viewModel.hitListData.observe(viewLifecycleOwner, Observer {
            adapter.setHitList(it)
        })

    }


    fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_LONG).show()
    }

    fun displayError(string: String) {
        showToast(string)
    }


    internal var itemListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemClick(view: View, position: Int) {
            val hit = adapter.getItemAtPosition(position)

            val bundle = Bundle()
            bundle.putParcelable("detail_hit", hit)

            view.findNavController()
                .navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }


    interface RecyclerItemListener {
        fun onItemClick(v: View, position: Int)
    }


}