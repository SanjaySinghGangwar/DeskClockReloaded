package com.ajna.deskclock.clock.mActivity.home

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ajna.deskclock.clock.R
import com.ajna.deskclock.clock.databinding.SkinsListviewBinding
import com.ajna.deskclock.clock.mProguard.ModelClasses.Skins.skins
import com.ajna.deskclock.clock.mUtils.Utils.showToast
import com.bumptech.glide.Glide


class HomeViewHolder(
    private val context: Context,
    private val itemBinding: SkinsListviewBinding,
    private val listener: HomeAdapter.ItemListener,
) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener, View.OnLongClickListener {

    private lateinit var items: skins

    init {
        itemBinding.mainLayout.setOnClickListener(this)
        itemBinding.mainLayout.setOnLongClickListener(this)
    }


    fun bind(item: skins) {
        this.items = item
        itemBinding.name.text = item.name
        Glide.with(context).load(item.image).into(itemBinding.sample);
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mainLayout -> {
                try {
                    listener.onClicked(
                        items.uid)
                } catch (e: Exception) {
                    showToast(context, e.message.toString())
                }

            }
        }

    }

    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {

            R.id.mainLayout -> {
                listener.onClicked(
                    items.uid)
            }
        }
        return false
    }

}