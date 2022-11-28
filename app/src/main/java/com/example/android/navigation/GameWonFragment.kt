/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener { view: View ->
            //view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        var args = GameWonFragmentArgs.fromBundle(requireArguments()) //was arguments!! before
        Toast.makeText(context,
            "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
            Toast.LENGTH_LONG).show()

        // 1. declare that there is an option menu
        setHasOptionsMenu(true);

        return binding.root
    }

    // 2. Let's inflate the option menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    // 3. create a method to create the share intent
    private fun getShareIntent() : Intent {
        var args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)            //action type used for sharing
        shareIntent.setType("text/plain")                       //MIME TYPE of shared data
            .putExtra(
                Intent.EXTRA_TEXT,                              //pass arguments to Intent
                getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
        return shareIntent
    }

    // 4. create a method to do the actual sharing
    private fun shareSuccess() {
        startActivity(getShareIntent()) // if several candidate activities, matching Intent filter, a chooser will be presented
    }

    // 5. action when share menu option selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()            //when selected itemId = "share"
        }
        return super.onOptionsItemSelected(item)
    }
}
