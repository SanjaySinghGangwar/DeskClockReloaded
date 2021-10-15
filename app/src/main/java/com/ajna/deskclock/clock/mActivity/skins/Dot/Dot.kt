package com.ajna.deskclock.clock.mActivity.skins.Dot

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ajna.deskclock.clock.R

class Dot : Fragment() {

    companion object {
        fun newInstance() = Dot()
    }

    private lateinit var viewModel: DotViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.dot, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DotViewModel::class.java)
        // TODO: Use the ViewModel
    }

}