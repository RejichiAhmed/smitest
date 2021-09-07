package com.smi.test.global.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager : ViewPager {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return false
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }
}