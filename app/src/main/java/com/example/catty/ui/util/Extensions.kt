package com.example.catty.ui.util

import android.animation.ValueAnimator
import android.widget.TextView

fun TextView.animateNumber(count: Int) {
    val animator = ValueAnimator.ofInt(0, count)
    animator.duration = 500
    animator.addUpdateListener { animation -> text = animation.animatedValue.toString() }
    animator.start()
}