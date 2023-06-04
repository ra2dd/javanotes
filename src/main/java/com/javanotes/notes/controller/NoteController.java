package com.javanotes.notes.controller;

import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Note;
import com.javanotes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class NoteController
{
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService)
    {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String listNotes(Model model)
    {
        List<NoteDto> notes = noteService.findAllNotes();
        model.addAttribute("notes", notes);

        String[] test = {"test1", "test2", "test3"};
        model.addAttribute("test", test);
        return "notes-list";
    }

    @GetMapping("/notes/new")
    public String createNoteForm(Model model)
    {
        Note note = new Note();
        model.addAttribute("note", note);
        return "notes-create";
    }

    @GetMapping("/notes/new")
    public String saveNote(@ModelAttribute("note") Note note)
    {
        noteService.saveNote(note);
        return "/redirect:/notes";
    }
}
