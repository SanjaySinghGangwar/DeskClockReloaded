package com.ajna.deskclock.clock.mActivity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ajna.deskclock.clock.BuildConfig
import com.ajna.deskclock.clock.mDialog.PermissionDialog
import com.ajna.deskclock.clock.R
import com.ajna.deskclock.clock.databinding.SplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class Splash : Fragment(), PermissionDialog.ItemListener {

    private var _binding: SplashBinding? = null
    private val bind get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var dialog: PermissionDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = SplashBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponent()
        checkForPermissions()

    }

    private fun checkForPermissions() {
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            sentToHome()
        } else {
            dialog.show(parentFragmentManager, "")
        }

    }

    private fun initAllComponent() {
        navController = Navigation.findNavController(requireView())
        dialog = PermissionDialog(this)
        bind.version.text = "V " + BuildConfig.VERSION_NAME
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun sentToHome() {
        lifecycleScope.launch(Dispatchers.Main) {
            if (isActive) {
                navController.navigate(R.id.splashToHome)
            }
        }
    }

    override fun onClicked(flag: Boolean) {
        if (flag) {
            askForLocationPermission()
            dismissAndSendToNext()
        } else {
            dismissAndSendToNext()
        }
    }

    private fun askForLocationPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            1)
    }

    fun dismissAndSendToNext() {
        dialog.dismiss()
        sentToHome()
    }
}