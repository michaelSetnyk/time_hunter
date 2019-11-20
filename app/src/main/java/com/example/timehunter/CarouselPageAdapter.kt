package com.example.timehunter

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.recyclerview.widget.RecyclerView

class ContactsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment_carousel_contacts ,container,false)
    }

    companion object{
        fun newInstance() = ContactsFragment()
    }
}

class EventsFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carousel_events, container, false)
    }

    companion object{
        fun newInstance() = EventsFragment()
    }
}

class GroupsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carousel_group, container, false)
    }

    companion object {
        fun newInstance() = GroupsFragment()
    }
}

class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carosel_create_event, container, false)
    }

    companion object {
        fun newInstance() = AddFragment()
    }
}
    class CarouselPageAdapter (fa:FragmentActivity, private val fragments:ArrayList<Fragment>): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position]
}

class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int): RecyclerView.ItemDecoration() {
    private val horizontalMarginInPx: Int =
        context.resources.getDimension(horizontalMarginInDp).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }
}
