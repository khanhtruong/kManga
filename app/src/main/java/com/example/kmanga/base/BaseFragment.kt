package com.example.kmanga.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.kmanga.util.DisposeBag

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private val baseViewModel: BaseViewModel by viewModels()

    private var _binding: ViewBinding? = null

    var bag: DisposeBag? = null

    @Suppress("UNCHECKED_CAST")
    val binding: VB
        get() = _binding as VB

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

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
        bag = DisposeBag(this)
    }

    fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun closeKeyboard() {
        // this will give us the view
        // which is currently focus
        // in this layout
        val view: View? = requireActivity().currentFocus

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            val manager: InputMethodManager? = requireActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager?
            manager?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showKeyboard(view: View) {
        val manager: InputMethodManager? = requireActivity().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager?
        manager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}