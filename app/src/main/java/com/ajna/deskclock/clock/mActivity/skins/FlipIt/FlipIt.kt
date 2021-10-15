package com.ajna.deskclock.clock.mActivity.skins.FlipIt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ajna.deskclock.clock.databinding.FlipItBinding

class FlipIt : Fragment() {


    private var _binding: FlipItBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val bind get() = _binding!!

    companion object {
        fun newInstance() = FlipIt()
    }

    private lateinit var viewModel: FlipItViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FlipItBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FlipItViewModel::class.java)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
    }

    private fun initAllComponents() {
    }
}