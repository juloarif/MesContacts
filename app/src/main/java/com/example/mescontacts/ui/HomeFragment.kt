package com.example.mescontacts.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.mescontacts.R
import com.example.mescontacts.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyler_view_notes.setHasFixedSize(true)
        recyler_view_notes.layoutManager= StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val  notes= NoteDatabase(it).getNoteDao().getAllNotes()
                recyler_view_notes.adapter= NotesAdapter(notes)

            }
        }

        button_add.setOnClickListener{
            val action= HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }


}
