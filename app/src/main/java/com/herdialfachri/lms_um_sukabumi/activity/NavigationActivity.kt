package com.herdialfachri.lms_um_sukabumi.activity

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.herdialfachri.lms_um_sukabumi.R
import com.herdialfachri.lms_um_sukabumi.adapter.ViewPagerAdapter

class NavigationActivity : AppCompatActivity() {

    private lateinit var slideViewPager: ViewPager
    private lateinit var dotIndicator: LinearLayout
    private lateinit var nextButton: Button
    private lateinit var dots: Array<TextView?>
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val viewPagerListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            setDotIndicator(position)
            nextButton.text = if (position == 2) "Selesai" else "Selanjutnya"

            val animIn = AnimationUtils.loadAnimation(this@NavigationActivity, R.anim.zoom_in)
            val animOut = AnimationUtils.loadAnimation(this@NavigationActivity, R.anim.zoom_out)
            slideViewPager.startAnimation(if (position == slideViewPager.currentItem) animIn else animOut)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            if (getItem(0) < 2)
                slideViewPager.setCurrentItem(getItem(1), true)
            else {
                val i = Intent(this@NavigationActivity, GetStartedActivity::class.java)
                startActivity(i)
                finish()
            }
        }

        slideViewPager = findViewById(R.id.slideViewPager)
        dotIndicator = findViewById(R.id.dotIndicator)

        viewPagerAdapter = ViewPagerAdapter(this)
        slideViewPager.adapter = viewPagerAdapter

        setDotIndicator(0)
        slideViewPager.addOnPageChangeListener(viewPagerListener)
    }

    private fun setDotIndicator(position: Int) {
        dots = arrayOfNulls(3)
        dotIndicator.removeAllViews()

        for (i in dots.indices) {
            dots[i] = TextView(this).apply {
                text = Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY)
                textSize = 35f
                setTextColor(resources.getColor(R.color.grey, applicationContext.theme))
            }
            dotIndicator.addView(dots[i])
        }
        dots[position]?.setTextColor(
            resources.getColor(
                R.color.biru_ummi,
                applicationContext.theme
            )
        )
    }

    private fun getItem(i: Int): Int {
        return slideViewPager.currentItem + i
    }
}
