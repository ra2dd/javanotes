package com.javanotes.notes.controller;

import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Category;
import com.javanotes.notes.models.Note;
import com.javanotes.notes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /*
        Controllers for creating new note
     */
    @GetMapping("/notes/new")
    public String createNoteForm(Model model)
    {
        Note note = new Note();
        model.addAttribute("note", note);
        return "notes-create";
    }

    @PostMapping("/notes/new")
    public String saveNote(@Valid @ModelAttribute("note") NoteDto noteDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "notes-create";
        }
        /*
        int intId = 1;
        long exampleId = intId;
        Category exampleCategory = new Category(exampleId, "test", null);
        Set<Category> exampleSet = new HashSet<>();
        exampleSet.add(exampleCategory);
        noteDto.setCategories(exampleSet);
         */
        noteService.saveNote(noteDto);
        return "redirect:/notes";
    }


    /*
        Controllers for updating a note
     */
    @GetMapping("/notes/{noteId}/update")
    public String updateNoteForm(@PathVariable("noteId") long noteId, Model model)
    {
        NoteDto noteDto = noteService.findNoteById(noteId);
        model.addAttribute("note", noteDto);
        return "note-update";
    }

    @PostMapping("/notes/{noteId}/update")
    public String updateNote(@PathVariable("noteId") long noteId, @Valid @ModelAttribute("note") NoteDto note,
                             BindingResult result)
    {
        if(result.hasErrors())
        {
            return "note-update";
        }
        note.setId(noteId);
        noteService.updateNote(note);
        return "redirect:/notes";
    }


    /*
        Controllers for detail view
     */
    @GetMapping("/notes/{noteId}")
    public String noteDetail(@PathVariable("noteId") long noteId, Model model)
    {
        NoteDto noteDto = noteService.findNoteById(noteId);
        model.addAttribute("note", noteDto);
        return "note-detail";
    }


    /*
        Controllers for deleting a note
     */
    @GetMapping("/notes/{noteId}/delete")
    public String deleteNote(@PathVariable("noteId") long noteId    )
    {
        noteService.deleteNote(noteId);
        return "redirect:/notes";
    }

    @GetMapping("/notes/search")
    public String searchNote(@RequestParam(value = "searchNoteQuery") String searchNoteQuery, Model model)
    {
        List<NoteDto> notes = noteService.searchNotes(searchNoteQuery);
        model.addAttribute("notes", notes);
        return "notes-list";
    }

}
