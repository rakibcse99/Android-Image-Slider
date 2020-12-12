package com.smarteist.autoimageslider.Transformations

import android.view.View
import com.smarteist.autoimageslider.SliderPager

class VerticalShutTransformation : SliderPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.translationX = -position * page.width
        page.cameraDistance = 999999999f
        if (position < 0.5 && position > -0.5) {
            page.visibility = View.VISIBLE
        } else {
            page.visibility = View.INVISIBLE
        }
        when {
            position < -1 -> {     // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.alpha = 0f
            }
            position <= 0 -> {    // [-1,0]
                page.alpha = 1f
                page.rotationX = 180 * (1 - Math.abs(position) + 1)
            }
            position <= 1 -> {    // (0,1]
                page.alpha = 1f
                page.rotationX = -180 * (1 - Math.abs(position) + 1)
            }
            else -> {    // (1,+Infinity]
                // This page is way off-screen to the right.
                page.alpha = 0f
            }
        }
    }
}