package com.payback.pixabayapplication.presentation.view.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.payback.pixabayapplication.presentation.view.adapter.SearchAdapter
import com.payback.pixabayapp.presentation.viewmodel.MainViewModel
import com.payback.pixabayapplication.R
import com.payback.pixabayapplication.databinding.FragmentImageListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageListFragment : Fragment() {

    private lateinit var binding: FragmentImageListBinding

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_image_list, container, false
        )
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
        binding.hitsRecyclerView.adapter = adapter
    }

    private fun actionListener(view: View) {
        binding.searchButton.setOnClickListener {
            onClickSearch(it)
        }
    }

    private fun setObservers() {
        viewModel.getQuery()

        viewModel.queryData.observe(viewLifecycleOwner, Observer { newValue ->
            val processedQuery = viewModel.processQuery(newValue)
            viewModel.getHitList(processedQuery)
        })


        viewModel.populateRecyclyerViewWithHitList(adapter)
    }



    fun onClickSearch(v: View) {

        if (TextUtils.isEmpty(binding.hitTitle.text)) {
            showToast("title cannot be empty")
        } else {
            viewModel.changeQuery(binding.hitTitle.text.toString())
            v.findNavController().navigate(R.id.action_imageListFragment_to_searchFragment)
        }
    }


    fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_LONG).show()
    }



    internal var itemListener: SearchFragment.RecyclerItemListener = object :
        SearchFragment.RecyclerItemListener {
        override fun onItemClick(view: View, position: Int) {
            val hit = adapter.getItemAtPosition(position)

            val bundle = Bundle()
            bundle.putParcelable("detail_hit", hit)

            view.findNavController()
                .navigate(R.id.action_imageListFragment_to_detailFragment, bundle)

        }
    }

}