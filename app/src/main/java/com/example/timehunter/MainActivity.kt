package com.example.timehunter

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var iconView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = applicationContext
        viewPager = findViewById(R.id.view_pager)
        iconView = findViewById(R.id.icon_group)
        val notificationList = findViewById<RecyclerView>(R.id.notifications)

        val beerEvent = GroupEventItem("Beer Night", "This Friday Night 9pm",R.drawable.beer)
        val raptorEvent = GroupEventItem("Raptors Game", "This Saturday 7:30 pm",R.drawable.raptors)
        val hciGroup = GroupEventItem("HCI Study Group", "December 3rd 12:00pm",R.drawable.hci)

        val beerNotification = Notification("Confirm Pickup",R.drawable.beer)
        val raptorNotification = Notification("Vote on HCI Study Day",R.drawable.hci)
        val HCINotification = Notification("All Riders Confirmed",R.drawable.raptors)


        val groupEventItems = arrayListOf(beerEvent,raptorEvent,hciGroup)
        val notifications = arrayListOf(beerNotification,raptorNotification,HCINotification)

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.5f * abs(position))
        }

        val layoutManger = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        layoutManger.scrollToPosition(0)

        notificationList.apply {
            setHasFixedSize(true)
            layoutManager=layoutManger
            adapter = NotificationAdapter(notifications)
        }

        prepCarousel(pageTransformer,context,groupEventItems)
        prepIcons(pageTransformer,context,groupEventItems)
    }

    fun prepIcons(pageTransformer: ViewPager2.PageTransformer,
                 context:Context,
                 items: ArrayList<GroupEventItem>){
        val itemDecoration = HorizontalMarginItemDecoration(context,
            R.dimen.viewpager_current_icon_horizontal_margin)
        println("icon page adapters")

        val layoutManger = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        layoutManger.scrollToPosition(1)
        val iconAdapter = IconPageAdapter(items)

        iconView.apply {
            setHasFixedSize(true)
            layoutManager=layoutManger
            adapter=iconAdapter
        }
        iconView.addItemDecoration(itemDecoration)
    }

    fun prepCarousel(pageTransformer: ViewPager2.PageTransformer,
                     context: Context,
                     items: ArrayList<GroupEventItem>){

        val fragments:ArrayList<Fragment> = ArrayList(items.map {GroupEventsFragment.newInstance(it)})

        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        val adapter = CarouselPageAdapter(this, fragments)
        viewPager.adapter = adapter
        viewPager.currentItem = 1
        viewPager.offscreenPageLimit = 1
        viewPager.addItemDecoration(itemDecoration)
        viewPager.setPageTransformer(pageTransformer)
    }
}
