package com.example.wildberriesweeksixflow

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.wildberriesweeksixflow.databinding.FragmentDownBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import kotlin.properties.Delegates


class DownFragment : Fragment() {
    lateinit var binding: FragmentDownBinding
    lateinit var fragmentViewModel: FragmentViewModel
    var countTimer by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewModel = ViewModelProvider(requireActivity()).get(FragmentViewModel::class.java)
        binding = FragmentDownBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentViewModel.startSeconds()


        binding.buttonPlay.setOnClickListener {
            fragmentViewModel.play()
        }

        binding.buttonPause.setOnClickListener {
            fragmentViewModel.pause()
        }

        binding.buttonReset.setOnClickListener {
            fragmentViewModel.reset()
        }


    }


    @SuppressLint("NotifyDataSetChanged", "ResourceType", "RepeatOnLifecycleWrongUsage")
    override fun onStart() {
        super.onStart()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fragmentViewModel.count.collect {
                    countTimer = it
                    if (countTimer % 20 == 0 && countTimer != 0) {
                        val rnd = Random()
                        val color: Int =
                            Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
                        binding.fragmentDown.setBackgroundColor(color)
                    }
                    if (countTimer == 0) {
                        binding.fragmentDown.setBackgroundColor(Color.WHITE)
                    }
                    binding.tvTimer.text = countTimer.toString()
                }
            }
        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = DownFragment()
    }
}

