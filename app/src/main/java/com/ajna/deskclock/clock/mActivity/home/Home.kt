package com.ajna.deskclock.clock.mActivity.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ajna.deskclock.clock.R
import com.ajna.deskclock.clock.databinding.HomeBinding
import com.ajna.deskclock.clock.mProguard.ModelClasses.Skins.skins
import com.ajna.deskclock.clock.mUtils.Utils.mToast
import com.ajna.deskclock.clock.mUtils.Utils.showToast


class Home : Fragment(), HomeAdapter.ItemListener, View.OnClickListener,
    HomeAdapter.LongItemListener {

    val TAG = "HOME"
    private var _binding: HomeBinding? = null
    private val bind get() = _binding!!
    private lateinit var skinList: List<skins>
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = HomeBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAlComponents()
        initRecyclerView()
        addDataToSkinList()

    }

    private fun initRecyclerView() {
        homeAdapter = HomeAdapter(requireContext(), this, this)
        bind.recycler.adapter = homeAdapter
    }

    private fun addDataToSkinList() {
        skinList = listOf(
            skins(1, "Classic", R.drawable.skinone),
            skins(2, "Analog", R.drawable.analog_skin),
            //skins(3, "Flip It", R.mipmap.ic_launcher),
            //skins(4, "Dot", R.mipmap.ic_launcher)

        )
        bind.setting.visibility = GONE
        homeAdapter.setItems(skinList)
    }

    private fun initAlComponents() {
        navController = Navigation.findNavController(requireView())
        bind.setting.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClicked(uid: Int) {
        when (uid) {
            1 -> {
                navController.navigate(R.id.homeToClassic)
            }
            2 -> {
                navController.navigate(R.id.home_to_retroSkin)
            }
            3 -> {
                navController.navigate(R.id.home_to_flipIt)
            }
            4 -> {
                navController.navigate(R.id.home_to_dot)
            }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.setting -> {
                showToast(requireContext(), "setting")
            }
        }
    }

    override fun onLongClicked(uid: Int) {
        mToast(requireContext(), uid.toString())
    }


}