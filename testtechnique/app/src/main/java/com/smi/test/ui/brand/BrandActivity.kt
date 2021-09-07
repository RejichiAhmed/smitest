package com.smi.test.ui.brand

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import com.smi.test.R
import com.smi.test.base.BaseActivity
import com.smi.test.databinding.ActivityBrandBinding
import com.smi.test.databinding.ActivityHomeBinding
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.ViewModelFactory
import javax.inject.Inject


class BrandActivity : BaseActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: BrandViewModel by viewModels { viewModelFactory }

    lateinit var binding : ActivityBrandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_brand)

        registerBindingAndBaseObservers(binding)
        registerBrandObservers()
    }


    /**
     * register UI Home activity Observers
     */
    private fun registerBrandObservers() {

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    /**
     * handling bottom navigation listener
     */
    override fun navigate(navigationTo: Navigation) {
        when (navigationTo) {

        }
    }

    /**
     * Register the UI for XMLBinding
     * Register the activity for base observers
     */
    private fun registerBindingAndBaseObservers(binding: ActivityBrandBinding) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        binding.picasso = getPicasso()
        binding.placeHolder = AppCompatResources.getDrawable(binding.root.context, R.mipmap.ic_launcher)
        registerBaseObservers(viewModel)
    }

}
