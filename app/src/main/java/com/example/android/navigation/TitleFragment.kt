package com.example.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

//        // Kotlin anonymous function: val sum = { a: Int, b: Int -> a + b } assigned as a literal!
//        binding.playButton.setOnClickListener { view: View ->
//            Navigation.findNavController(view).navigate(
//                R.id.action_titleFragment_to_gameFragment //generated into navigation.xml
//            )
//            //optimization 1: version using kotlin extensions functions
//            //view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment);
//        }

        //optimization 2: Navigation can create the listener for you!
        binding.playButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment))

        return binding.root // the root of the layout we just inflated
    }
}