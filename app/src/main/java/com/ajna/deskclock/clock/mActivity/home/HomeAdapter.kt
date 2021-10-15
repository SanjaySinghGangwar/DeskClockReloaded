package com.ajna.deskclock.clock.mActivity.home

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ajna.deskclock.clock.databinding.SkinsListviewBinding
import com.ajna.deskclock.clock.mProguard.ModelClasses.Skins.skins


class HomeAdapter(private val context: Context, private val listener: ItemListener,private val longListener: LongItemListener) :
    RecyclerView.Adapter<HomeViewHolder>() {


    private val items = ArrayList<skins>()
    private var lastPosition = -1
    val TAG = "Home Adapter "


    fun setItems(items: List<skins>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    interface ItemListener {
        fun onClicked(uid: Int)
    }

    interface LongItemListener {
        fun onLongClicked(uid: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding: SkinsListviewBinding =
            SkinsListviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(context, binding, listener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
        setAnimation(holder.itemView, position);
    }


    override fun getItemCount(): Int {
        return items.size
    }

    private fun setAnimation(itemView: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.slide_out_right)
            itemView.startAnimation(animation)
            lastPosition = position
        }

    }


}