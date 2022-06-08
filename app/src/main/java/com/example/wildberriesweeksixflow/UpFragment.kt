package com.example.wildberriesweeksixflow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.wildberriesweeksixflow.databinding.FragmentUpBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class UpFragment : Fragment() {
    lateinit var binding: FragmentUpBinding
    lateinit var fragmentViewModel: FragmentViewModel
    var randomValue: Int? = null
    var fullValue: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewModel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
        binding = FragmentUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    @SuppressLint("NotifyDataSetChanged", "SetTextI18n", "RepeatOnLifecycleWrongUsage")
    override fun onStart() {
        super.onStart()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentViewModel.randomValue.collect {
                    randomValue = it
                    if (randomValue == 10) {
                        binding.tvPi.text = "${3.14}"
                    } else {
                        binding.tvPi.text = "${binding.tvPi.text}$randomValue"
                    }
                }
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = UpFragment()
    }
}