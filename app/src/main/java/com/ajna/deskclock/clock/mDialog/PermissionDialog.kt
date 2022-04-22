package com.ajna.deskclock.clock.mDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ajna.deskclock.clock.R
import com.ajna.deskclock.clock.databinding.PermissionDialogFragmentBinding

class PermissionDialog(private val listener: ItemListener) : DialogFragment(),
    View.OnClickListener {

    private var bind: PermissionDialogFragmentBinding? = null
    private val binding get() = bind!!

    interface ItemListener {
        fun onClicked(flag: Boolean)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dialog!!.setCancelable(false)
        bind = PermissionDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllComponents()
    }

    private fun initAllComponents() {

        bind!!.description.text =
            getString(R.string.location_descripton_two)+"${getString(R.string.app_name)} "+getString(R.string.location_descrippton_two)

        bind!!.allow.setOnClickListener(this)
        bind!!.cancel.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.allow -> {
                listener.onClicked(true)
            }
            R.id.cancel -> {
                listener.onClicked(false)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        bind = null
    }
}