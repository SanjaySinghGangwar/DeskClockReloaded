package com.ajna.deskclock.clock.mActivity.skins.Classic

import android.annotation.SuppressLint
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
import com.ajna.deskclock.clock.databinding.ClassicBinding
import com.ajna.deskclock.clock.mProguard.ModelClasses.weatherAPI.weatherData
import com.ajna.deskclock.clock.mUtils.GPSTracker
import com.ajna.deskclock.clock.mUtils.Utils.degreeToFahrenheit
import com.ajna.deskclock.clock.mUtils.Utils.openGpsIfOff
import com.ajna.deskclock.clock.retrofit.mApiInterface
import com.ajna.deskclock.clock.retrofit.mRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt


class Classic : Fragment(), View.OnClickListener {

    val TAG = "Classic Activity"

    private var _binding: ClassicBinding? = null
    private val bind get() = _binding!!
    private lateinit var navController: NavController
    var gps: GPSTracker? = null

    companion object {
        fun newInstance() = Classic()
    }

    private lateinit var viewModel: ClassicViewModel
    lateinit var respo: Respo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ClassicBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
    }

    private fun initAllComponents() {

        navController = Navigation.findNavController(requireView())
        bind.backToHome.setOnClickListener(this)

        //Instance for the repos
        val apiService: mApiInterface = mRetrofitClient.getClient(context)!!
        gps = GPSTracker(requireContext())

        respo = Respo(apiService, gps!!)

        viewModel = getViewModel()

        openGpsIfOff(requireContext())

        updateUiLocationData()
        updateTime()
        updateDate()

    }

    private fun updateDate() {
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch(Dispatchers.IO) {
                val data = viewModel.date()
                withContext(Dispatchers.Main) {
                    bind.date.text = data
                }
                updateTime()
            }
        }, 1000)
    }

    private fun updateTime() {
        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch(Dispatchers.IO) {
                val data = viewModel.time()
                val sec = viewModel.seconds()
                withContext(Dispatchers.Main) {
                    bind.time.text = data
                    //mToast(requireContext(), sec)
                    setSrc(sec)

                }
                updateTime()
            }
        }, 1000)
    }

    private fun setSrc(sec: String) {
        when (sec) {

            "00" -> {
                bind.progressBarOne.progress = 0
                bind.progressBarTwo.progress = 0
                bind.progressBarThree.progress = 0
                bind.progressBarFour.progress = 0
            }
            "01" -> {
                bind.progressBarOne.progress = 6.67.roundToInt()
            }
            "02" -> {
                bind.progressBarOne.progress = (6.67 * 2).roundToInt()
            }
            "03" -> {
                bind.progressBarOne.progress = (6.67 * 3).roundToInt()
            }
            "04" -> {
                bind.progressBarOne.progress = (6.67 * 4).roundToInt()
            }
            "05" -> {
                bind.progressBarOne.progress = (6.67 * 5).roundToInt()
            }
            "06" -> {
                bind.progressBarOne.progress = (6.67 * 6).roundToInt()
            }
            "07" -> {
                bind.progressBarOne.progress = (6.67 * 7).roundToInt()
            }
            "08" -> {
                bind.progressBarOne.progress = (6.67 * 8).roundToInt()
            }
            "09" -> {
                bind.progressBarOne.progress = (6.67 * 9).roundToInt()
            }
            "10" -> {
                bind.progressBarOne.progress = (6.67 * 10).roundToInt()
            }
            "11" -> {
                bind.progressBarOne.progress = (6.67 * 11).roundToInt()
            }
            "12" -> {
                bind.progressBarOne.progress = (6.67 * 12).roundToInt()
            }
            "13" -> {
                bind.progressBarOne.progress = (6.67 * 13).roundToInt()
            }
            "14" -> {
                bind.progressBarOne.progress = (6.67 * 14).roundToInt()

            }
            "15" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()

            }
            "16" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 1).roundToInt()

            }
            "17" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 2).roundToInt()

            }
            "18" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 3).roundToInt()

            }
            "19" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 4).roundToInt()

            }
            "20" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 5).roundToInt()

            }
            "21" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 6).roundToInt()

            }
            "22" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 7).roundToInt()

            }
            "23" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 8).roundToInt()

            }
            "24" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 9).roundToInt()

            }
            "25" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 10).roundToInt()

            }
            "26" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 11).roundToInt()

            }
            "27" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 12).roundToInt()

            }
            "28" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 13).roundToInt()

            }
            "29" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 14).roundToInt()

            }
            "30" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()

            }
            "31" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 1).roundToInt()

            }
            "32" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 2).roundToInt()

            }
            "33" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 3).roundToInt()

            }
            "34" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 4).roundToInt()

            }
            "35" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 5).roundToInt()

            }
            "36" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 6).roundToInt()

            }
            "37" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 7).roundToInt()

            }
            "38" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 8).roundToInt()

            }
            "39" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 9).roundToInt()

            }
            "40" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 10).roundToInt()

            }
            "41" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 11).roundToInt()

            }
            "42" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 12).roundToInt()

            }
            "43" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 13).roundToInt()

            }
            "44" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 14).roundToInt()

            }
            "45" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()

            }
            "46" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 1).roundToInt()

            } "47" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 2).roundToInt()

            } "48" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 3).roundToInt()

            } "49" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 4).roundToInt()

            } "50" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 5).roundToInt()

            } "51" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 6).roundToInt()

            } "52" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 7).roundToInt()

            } "53" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 8).roundToInt()

            } "54" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 9).roundToInt()

            } "55" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 10).roundToInt()

            } "56" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 11).roundToInt()

            } "57" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 12).roundToInt()

            } "58" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 13).roundToInt()

            } "59" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 14).roundToInt()

            } "60" -> {
                bind.progressBarOne.progress = (6.67 * 15).roundToInt()
                bind.progressBarTwo.progress = (6.67 * 15).roundToInt()
                bind.progressBarThree.progress = (6.67 * 15).roundToInt()
                bind.progressBarFour.progress = (6.67 * 15).roundToInt()

            }
        }
    }

    private fun updateUiLocationData() {
        lifecycleScope.launch(Dispatchers.Main) {
            bindUi(
                viewModel.weather().data!!
            )

        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindUi(data: weatherData) {
        bind.location.text = data.name
        bind.wind.text = data.wind.speed.roundToInt().toString() + " M/S"
        bind.temperature.text = degreeToFahrenheit(data.main.temp).toString() + "°C"

    }

    @JvmName("getViewModel1")
    private fun getViewModel(): ClassicViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ClassicViewModel(respo) as T
            }
        })[ClassicViewModel::class.java]
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.backToHome -> {
                navController.popBackStack()
            }
        }
    }


}