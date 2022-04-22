package com.ajna.deskclock.clock.mActivity.Setting

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ajna.deskclock.clock.R
import com.ajna.deskclock.clock.databinding.SettingBinding
import com.ajna.deskclock.clock.mProguard.mSharedPreference.AppSharePreference

class Setting : Fragment(), View.OnClickListener {

    private var _binding: SettingBinding? = null
    private val bind get() = _binding!!

    private var prefData: AppSharePreference? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiAllComponents()
        updateTemperatureUI()
        updateSoundUI()
    }


    private fun intiAllComponents() {
        prefData = AppSharePreference(requireContext())
        bind.back.setOnClickListener(this)
        bind.celsius.setOnClickListener(this)
        bind.fahrenheit.setOnClickListener(this)
        bind.soundOff.setOnClickListener(this)
        bind.soundOn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.soundOff -> {
                prefData?.isClockSound = false
                updateSoundUI()
            }
            R.id.soundOn -> {
                prefData?.isClockSound = true
                updateSoundUI()
            }
            R.id.celsius -> {
                prefData?.isTemperatureFahrenheit = false
                updateTemperatureUI()

            }
            R.id.fahrenheit -> {
                prefData?.isTemperatureFahrenheit = true
                updateTemperatureUI()
            }
            R.id.back -> {
                requireView().findNavController().popBackStack()
            }
        }
    }

    private fun updateTemperatureUI() {
        if (prefData!!.isTemperatureFahrenheit) {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    bind.fahrenheit.setTextColor(requireContext().resources.getColor(R.color.white))
                    bind.celsius.setTextColor(requireContext().resources.getColor(R.color.black))

                    bind.fahrenheit.setBackgroundColor(requireContext().resources.getColor(R.color.primary))
                    bind.celsius.setBackgroundColor(requireContext().resources.getColor(R.color.white))
                }
                Configuration.UI_MODE_NIGHT_YES -> {
                    bind.celsius.setTextColor(requireContext().resources.getColor(R.color.white))
                    bind.celsius.setBackgroundColor(requireContext().resources.getColor(R.color.black))

                    bind.fahrenheit.setTextColor(requireContext().resources.getColor(R.color.black))
                    bind.fahrenheit.setBackgroundColor(requireContext().resources.getColor(R.color.white))
                }
            }


        } else {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    bind.fahrenheit.setTextColor(requireContext().resources.getColor(R.color.black))
                    bind.celsius.setTextColor(requireContext().resources.getColor(R.color.white))

                    bind.celsius.setBackgroundColor(requireContext().resources.getColor(R.color.primary))
                    bind.fahrenheit.setBackgroundColor(requireContext().resources.getColor(R.color.white))
                }
                Configuration.UI_MODE_NIGHT_YES -> {

                    bind.fahrenheit.setTextColor(requireContext().resources.getColor(R.color.white))
                    bind.fahrenheit.setBackgroundColor(requireContext().resources.getColor(R.color.black))

                    bind.celsius.setTextColor(requireContext().resources.getColor(R.color.black))
                    bind.celsius.setBackgroundColor(requireContext().resources.getColor(R.color.white))
                }
            }

        }

    }

    private fun updateSoundUI() {
        if (prefData!!.isClockSound) {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    bind.soundOn.setTextColor(requireContext().resources.getColor(R.color.white))
                    bind.soundOff.setTextColor(requireContext().resources.getColor(R.color.black))

                    bind.soundOn.setBackgroundColor(requireContext().resources.getColor(R.color.primary))
                    bind.soundOff.setBackgroundColor(requireContext().resources.getColor(R.color.white))
                }
                Configuration.UI_MODE_NIGHT_YES -> {
                    bind.soundOff.setTextColor(requireContext().resources.getColor(R.color.white))
                    bind.soundOff.setBackgroundColor(requireContext().resources.getColor(R.color.black))

                    bind.soundOn.setTextColor(requireContext().resources.getColor(R.color.black))
                    bind.soundOn.setBackgroundColor(requireContext().resources.getColor(R.color.white))
                }
            }


        } else {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    bind.soundOff.setTextColor(requireContext().resources.getColor(R.color.white))
                    bind.soundOn.setTextColor(requireContext().resources.getColor(R.color.black))

                    bind.soundOff.setBackgroundColor(requireContext().resources.getColor(R.color.primary))
                    bind.soundOn.setBackgroundColor(requireContext().resources.getColor(R.color.white))
                }
                Configuration.UI_MODE_NIGHT_YES -> {
                    bind.soundOff.setTextColor(requireContext().resources.getColor(R.color.black))
                    bind.soundOff.setBackgroundColor(requireContext().resources.getColor(R.color.white))

                    bind.soundOn.setTextColor(requireContext().resources.getColor(R.color.white))
                    bind.soundOn.setBackgroundColor(requireContext().resources.getColor(R.color.black))
                }
            }


        }
    }
}