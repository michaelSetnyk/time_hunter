package com.example.timehunter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = applicationContext
        viewPager = findViewById(R.id.view_pager)

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
        }

        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )

        val fragments = arrayListOf(
            ContactsFragment.newInstance(),
            EventsFragment.newInstance(),
            GroupsFragment.newInstance(),
            AddFragment.newInstance()
        )

        val adapter = CarouselPageAdapter(this, fragments)
        viewPager.adapter = adapter
        viewPager.currentItem = 1
        viewPager.offscreenPageLimit = 1
        viewPager.addItemDecoration(itemDecoration)
        viewPager.setPageTransformer(pageTransformer)
    }

}
