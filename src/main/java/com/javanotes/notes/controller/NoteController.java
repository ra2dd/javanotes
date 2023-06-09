package com.javanotes.notes.controller;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Category;
import com.javanotes.notes.models.Note;
import com.javanotes.notes.models.UserEntity;
import com.javanotes.notes.security.SecurityUtil;
import com.javanotes.notes.service.CategoryService;
import com.javanotes.notes.service.NoteService;
import com.javanotes.notes.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NoteController
{
    private NoteService noteService;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public NoteController(NoteService noteService, CategoryService categoryService, UserService userService)
    {
        this.noteService = noteService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    /*
        Controller for listing notes
     */
    @GetMapping("/notes")
    public String listNotes(Model model)
    {
        List<NoteDto> notes = noteService.findAllNotes();
        model.addAttribute("notes", notes);

        return "notes-list";
    }

    @GetMapping("/my-notes")
    public String listUserNotes(Model model)
    {
        UserEntity sessionUser = new UserEntity();
        String sessionUserUsername = SecurityUtil.getSessionUser();
        if(sessionUserUsername != null)
        {
            sessionUser = userService.findByUsername(sessionUserUsername);
        }

        List<NoteDto> userNotesDto = noteService.findAllUserNotes(sessionUser);
        model.addAttribute("notes", userNotesDto);

        return "my-notes-list";
    }

    /*
        Controllers for creating new note
     */
    @GetMapping("/notes/new")
    public String createNoteForm(Model model)
    {
        Note note = new Note();
        model.addAttribute("note", note);
        return "note-create";
    }

    @PostMapping("/notes/new")
    public String saveNote(@Valid @ModelAttribute("note") NoteDto noteDto, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "note-create";
        }

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
    public String updateNote(@PathVariable("noteId") long noteId, @Valid @ModelAttribute("note") NoteDto noteDto,
                             BindingResult result)
    {
        if(result.hasErrors())
        {
            return "redirect:/notes/{noteId}";
        }

        noteDto.setId(noteId);
        noteService.updateNote(noteDto);
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

        UserEntity sessionUser = new UserEntity();
        String sessionUserUsername = SecurityUtil.getSessionUser();
        if(sessionUserUsername != null)
        {
            sessionUser = userService.findByUsername(sessionUserUsername);
        }
        model.addAttribute("sessionUser", sessionUser);

        System.out.println(sessionUser.getId());
        System.out.println(noteDto.getCreatedBy().getId());
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

    /*
        Controllers for sorting and searching
     */
    @GetMapping("/notes/search")
    public String searchNote(@RequestParam(value = "searchNoteQuery") String searchNoteQuery, Model model)
    {
        List<NoteDto> notes = noteService.searchNotes(searchNoteQuery);
        model.addAttribute("notes", notes);
        return "notes-list";
    }

    @GetMapping("/my-notes/order-create-time")
    public String orderNoteByCreateTime(@RequestParam(value = "orderCreateTimeQuery") String orderCreateTimeQuery, Model model, HttpServletRequest request)
    {
        UserEntity sessionUser = new UserEntity();
        String sessionUserUsername = SecurityUtil.getSessionUser();
        if(sessionUserUsername != null)
        {
            sessionUser = userService.findByUsername(sessionUserUsername);
        }

        List<NoteDto> notesOrdered = noteService.orderNotesByCreateTime(orderCreateTimeQuery, sessionUser);
        model.addAttribute("notes", notesOrdered);

        /*
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
        {
            cookies[0].setAttribute("ordering", orderCreateTimeQuery);
        }
        else
        {
            Cookie cookie = new Cookie("ordering", orderCreateTimeQuery);
        }

         */

        return "my-notes-list";
    }


    /*
        Controllers for assigning categories to note
     */
    @GetMapping("/notes/{noteId}/assign-categories")
    public String getCategories(@PathVariable("noteId") long noteId, Model model)
    {
        NoteDto noteDto = noteService.findNoteById(noteId);
        model.addAttribute("note", noteDto);

        List<CategoryDto> allCategoriesDto = new ArrayList<>(categoryService.findAllCategories());
        model.addAttribute("noteCategoriesDto", allCategoriesDto);

        return "note-assign-categories";
    }

    @PostMapping("/notes/{noteId}/assign-categories")
    public String assignCategories(@PathVariable("noteId") long noteId, @ModelAttribute("note") NoteDto noteDto)
    {
        noteDto.setId(noteId);
        System.out.println(noteDto);
        noteService.assignCategoriesToNote(noteDto);
        return "redirect:/notes/{noteId}";
    }
}
