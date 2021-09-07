package com.smi.test.ui.home.task2

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
import com.smi.test.databinding.FragmentTwoBinding
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.ViewModelFactory
import com.smi.test.global.utils.DebugLog
import com.smi.test.global.utils.TAG
import com.smi.test.ui.adapter.BrandsAdapter
import com.smi.test.ui.home.HomeActivity
import javax.inject.Inject


class TwoFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var brandsAdapter: BrandsAdapter

    lateinit var bind : FragmentTwoBinding

    private val viewModel: TwoViewModel by viewModels { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_two, container, false)

        // Register the UI for XMLBinding
        bind = FragmentTwoBinding.bind(view)
        bind.viewModel = viewModel
        bind.setLifecycleOwner(viewLifecycleOwner)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBaseObserver(viewModel)
        registerTwoObservers()
    }


    /**
     * register UI Two Observers
     */
    private fun registerTwoObservers() {
        registerRecycler()
    }

    private fun registerRecycler() {
        brandsAdapter.listener = viewModel
        bind.recyclerTwoContent.layoutManager = GridLayoutManager(activity,3, RecyclerView.VERTICAL, false)
        bind.recyclerTwoContent.adapter = brandsAdapter
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

    companion object {
        fun newInstance(): TwoFragment = TwoFragment()
    }
}





