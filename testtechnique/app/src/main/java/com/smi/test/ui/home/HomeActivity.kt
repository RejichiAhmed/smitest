package com.smi.test.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.smi.test.R
import com.smi.test.base.BaseActivity
import com.smi.test.data.model.Brands
import com.smi.test.databinding.ActivityHomeBinding
import com.smi.test.global.helper.Navigation
import com.smi.test.global.helper.ViewModelFactory
import com.smi.test.global.utils.DebugLog
import com.smi.test.global.utils.ExtraKeys
import com.smi.test.global.utils.TAG
import com.smi.test.ui.auth.SignInActivity
import com.smi.test.ui.brand.BrandActivity
import com.smi.test.ui.home.task1.OneFragment
import com.smi.test.ui.home.task2.TwoFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class HomeActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        setViewPager()
        registerBindingAndBaseObservers(binding)
        registerHomeObservers()
    }


    /**
     * register UI Home activity Observers
     */
    private fun registerHomeObservers() {

    }

    private fun setViewPager() {
        val pageOneFragment = OneFragment.newInstance()
        val pageTwoFragment = TwoFragment.newInstance()

        binding.pager.adapter = PagerAdapter(
            supportFragmentManager,
            listOf(pageOneFragment, pageTwoFragment)
        )
        binding.tabLayout.setupWithViewPager(binding.pager)
    }


    override fun onBackPressed() {
        if (binding.pager.currentItem == 0) {
            finish()
        } else {
            binding.pager.setCurrentItem(binding.pager.currentItem - 1, true)
        }
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
            is Navigation.SignInActivityNavigation -> navigateToSignIn()
        }
    }

    /**
     * Register the UI for XMLBinding
     * Register the activity for base observers
     */
    private fun registerBindingAndBaseObservers(binding: ActivityHomeBinding) {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        registerBaseObservers(viewModel)
    }

    fun navigateToBrand(brand: Brands) {
        Intent(this, BrandActivity::class.java).let {
            it.putExtra(ExtraKeys.HomeActivity.BRAND_EXTRA_VALUE_KEY, brand)
            startActivity(it)
        }
    }

    fun navigateToSignIn() {
        Intent(this, SignInActivity::class.java).let {
            startActivity(it)
            finish()
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector    }
}
