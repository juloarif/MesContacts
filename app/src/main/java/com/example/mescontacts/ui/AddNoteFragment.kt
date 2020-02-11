package com.example.mescontacts.ui


import android.app.LauncherActivity
import android.content.pm.LauncherApps
import android.os.AsyncTask
import android.os.Bundle
import android.renderscript.Script
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation

import com.example.mescontacts.R
import com.example.mescontacts.db.Note
import com.example.mescontacts.db.NoteDatabase
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class AddNoteFragment : BaseFragment() {

    private var note: Note?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note= AddNoteFragmentArgs.fromBundle(it).Note
            edit_text_Titre.setText(note?.titre)
            edit_text_Note.setText(note?.note)
        }

        button_save.setOnClickListener {View->

            val noteTitle= edit_text_Titre.text.toString().trim()
            val  noteBody= edit_text_Note.text.toString().trim()

            if(noteTitle.isEmpty()){
                edit_text_Titre.error= "Veuillez entrer un titre"
                edit_text_Titre.requestFocus()
                return@setOnClickListener
            }

            if(noteBody.isEmpty()){
                edit_text_Note.error= "Veuillez entrer une note"
                edit_text_Note.requestFocus()
                return@setOnClickListener
            }
        launch {
            val note= Note(noteTitle, noteBody)
            context?.let {
                NoteDatabase(it).getNoteDao().addNote(note)
                it.toast("Note enregistrée")

                val action=  AddNoteFragmentDirections.actionSaveNote()
                Navigation.findNavController(View).navigate(action)
            }
            }


            //saveNote(note)
        }
    }




}
