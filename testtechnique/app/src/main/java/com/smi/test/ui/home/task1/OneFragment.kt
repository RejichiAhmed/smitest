package com.smi.test.ui.home.task1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smi.test.R
import com.smi.test.base.BaseFragment
import com.smi.test.databinding.FragmentOneBinding
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.ViewModelFactory
import com.smi.test.global.utils.DebugLog
import com.smi.test.global.utils.TAG
import com.smi.test.ui.adapter.BrandsAdapter
import com.smi.test.ui.home.HomeActivity
import javax.inject.Inject


class OneFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: OneViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var brandsAdapter: BrandsAdapter

    lateinit var bind : FragmentOneBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_one, container, false)


        // Register the UI for XMLBinding
        bind = FragmentOneBinding.bind(view)
        bind.viewModel = viewModel
        bind.setLifecycleOwner(viewLifecycleOwner)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBaseObserver(viewModel)
        registerOneObservers()
    }


    /**
     * register UI One Observers
     */
    private fun registerOneObservers() {
        registerRecycler()
    }


    /**
     * handling navigation event
     */
    override fun navigate(navigationTo: Navigation) {
        when (navigationTo) {
            is Navigation.BrandActivityNavigation -> {
                DebugLog.d(TAG, "User is loggedIn value is ${navigationTo.brand}")
                (activity as HomeActivity).navigateToBrand(navigationTo.brand)
            }
        }
    }

    private fun registerRecycler() {
        brandsAdapter.listener = viewModel
        bind.recyclerTwoContent.layoutManager = GridLayoutManager(activity,3, RecyclerView.VERTICAL, false)
        bind.recyclerTwoContent.adapter = brandsAdapter
    }

    companion object {
        fun newInstance(): OneFragment = OneFragment()
    }

}





