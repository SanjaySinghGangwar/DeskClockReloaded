package com.ajna.deskclock.clock.mActivity.skins.Analog

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ajna.deskclock.clock.R
import com.ajna.deskclock.clock.Respo
import com.ajna.deskclock.clock.databinding.AnalogSkinBinding
import com.ajna.deskclock.clock.mProguard.ModelClasses.weatherAPI.weatherData
import com.ajna.deskclock.clock.mProguard.mSharedPreference.AppSharePreference
import com.ajna.deskclock.clock.mUtils.GPSTracker
import com.ajna.deskclock.clock.mUtils.Utils
import com.ajna.deskclock.clock.mUtils.Utils.mToast
import com.ajna.deskclock.clock.mUtils.Utils.openGpsIfOff
import com.ajna.deskclock.clock.retrofit.mApiInterface
import com.ajna.deskclock.clock.retrofit.mRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class AnalogSkin : Fragment(), View.OnClickListener {


    private var _binding: AnalogSkinBinding? = null
    private val bind get() = _binding!!
    private lateinit var navController: NavController



    lateinit var respo: Respo
    private lateinit var analogSkinViewModel: AnalogSkinViewModel
    var gps: GPSTracker? = null


    private lateinit var mediaPlayer: MediaPlayer
    private var mSharePreference: AppSharePreference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = AnalogSkinBinding.inflate(inflater, container, false)
        return bind.root
    }

    private fun initAllComponents() {
        //Instance for the repos
        val apiService: mApiInterface = mRetrofitClient.getClient(context)!!
        gps = GPSTracker(requireContext())

        respo = Respo(apiService, gps!!)

        analogSkinViewModel = getViewModel()


        openGpsIfOff(requireContext())

        updateUiLocationData()
        updateTimeAndDate()
        bind.backToHome.setOnClickListener(this)
        navController = Navigation.findNavController(requireView())

        mSharePreference = AppSharePreference(requireContext())

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.tick)

    }

    override fun onResume() {
        super.onResume()
        if (mSharePreference!!.isClockSound) {
            mediaPlayer.start()
            mediaPlayer.isLooping = true
        }
    }

    private fun updateTimeAndDate() {
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch(Dispatchers.IO) {

                val date = analogSkinViewModel.date()
                withContext(Dispatchers.Main) {
                    bind.date.text = date
                    bind.clock.setTime(
                        analogSkinViewModel.hours().toInt(),
                        analogSkinViewModel.minutes().toInt(),
                        analogSkinViewModel.seconds().toInt()
                    )
                }

            }
            updateTimeAndDate()
        }, 1000)
    }

    private fun updateUiLocationData() {
        lifecycleScope.launch(Dispatchers.Main) {
            bindUi(
                analogSkinViewModel.weather().data!!
            )

        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindUi(data: weatherData) {
        bind.location.text = data.name
        bind.wind.text = data.wind.speed.roundToInt().toString() + " M/S"
        bind.temperature.text = if (mSharePreference!!.isTemperatureFahrenheit) {
            Utils.kelvinToFahrenheit(data.main.temp).toString() + "°F"
        } else {
            Utils.kelvinToDegree(data.main.temp).toString() + "°C"
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
        setUIColor()
    }

    private fun setUIColor() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                bind.clock.setHourTint(requireContext().resources.getColor(R.color.black))
                bind.clock.setMinuteTint(requireContext().resources.getColor(R.color.black))
                bind.clock.setSecondTint(requireContext().resources.getColor(R.color.black))
                bind.clock.setFaceTint(requireContext().resources.getColor(R.color.black))
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                bind.clock.setHourTint(requireContext().resources.getColor(R.color.white))
                bind.clock.setMinuteTint(requireContext().resources.getColor(R.color.white))
                bind.clock.setSecondTint(requireContext().resources.getColor(R.color.white))
                bind.clock.setFaceTint(requireContext().resources.getColor(R.color.white))
            }
        }
    }


    @JvmName("getViewModel1")
    private fun getViewModel(): AnalogSkinViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return AnalogSkinViewModel(respo) as T
            }
        })[AnalogSkinViewModel::class.java]
    }

    /* override fun onDestroyView() {
         super.onDestroyView()
         _binding = null
     }*/

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backToHome -> {
                stopSound()
                navController.popBackStack()
            }
        }
    }

    fun stopSound() {

        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.stop()
            //audio is paused here
        }
    }


    override fun onPause() {
        stopSound()
        super.onPause()
    }
}