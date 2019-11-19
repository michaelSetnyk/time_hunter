package com.example.timehunter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_carousel_main_screen.view.*

class MainActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.view_pager)
        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ViewPagerAdapter()
        viewPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private class ViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {

        private val colors = intArrayOf(
            android.R.color.black,
            android.R.color.holo_red_light,
            android.R.color.holo_blue_dark,
            android.R.color.holo_purple
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
            PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.fragment_carousel_main_screen, parent, false))

        override fun getItemCount(): Int = colors.size

        override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
            txtMain.text = "item $position"
            card.setBackgroundResource(colors[position])
        }
    }

    class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)


}
