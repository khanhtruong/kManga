package com.example.kmanga.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding>(private val backgroundDrawableRes: Int) :
    DialogFragment() {

        private val viewModel: BaseViewModel by viewModels()

        private var _binding: ViewBinding? = null

        @Suppress("UNCHECKED_CAST")
        val binding: VB
            get() = _binding as VB

        abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

        abstract fun onReady(view: View, savedInstanceState: Bundle?)

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = bindingInflater.invoke(inflater, container, false)
            return requireNotNull(_binding).root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setupBottomSheetDialog()
            onReady(view, savedInstanceState)
        }

        private fun setupBottomSheetDialog() {
            dialog?.setOnShowListener {
                this.dialog?.window?.setBackgroundDrawable(
                    ResourcesCompat.getDrawable(
                        this.requireContext().resources,
                        backgroundDrawableRes,
                        null
                    )
                )
            }
        }
    }