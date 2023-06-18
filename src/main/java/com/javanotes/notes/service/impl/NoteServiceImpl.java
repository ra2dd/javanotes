package com.javanotes.notes.service.impl;

import com.javanotes.notes.dto.CategoryDto;
import com.javanotes.notes.dto.NoteDto;
import com.javanotes.notes.models.Category;
import com.javanotes.notes.models.Note;
import com.javanotes.notes.models.UserEntity;
import com.javanotes.notes.repository.CategoryRepository;
import com.javanotes.notes.repository.NoteRepository;
import com.javanotes.notes.repository.UserRepository;
import com.javanotes.notes.security.SecurityUtil;
import com.javanotes.notes.service.CategoryService;
import com.javanotes.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.javanotes.notes.mapper.NoteMapper.mapToNote;
import static com.javanotes.notes.mapper.NoteMapper.mapToNoteDto;

@Service
public class NoteServiceImpl implements NoteService
{
    private NoteRepository noteRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, CategoryRepository categoryRepository, UserRepository userRepository)
    {
        this.noteRepository = noteRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    /*
        Method for lisitng notes
     */
    @Override
    public List<NoteDto> findAllNotes()
    {
        List<Note> notes = noteRepository.findAll();
        return notes.stream().map((note) -> mapToNoteDto(note)).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> findAllUserNotes(UserEntity sessionUser)
    {
        List<Note> notes = noteRepository.findAll();
        List<Note> userNotes = new ArrayList<>();
        for(Note note :notes)
        {
            if(note.getCreatedBy().getId() == sessionUser.getId())
            {
                userNotes.add(note);
            }
        }
        return userNotes.stream().map((note) -> mapToNoteDto(note)).collect(Collectors.toList());
    }


    /*
        Method for creating, updating, deleting notes
    */
    @Override
    public Note saveNote(NoteDto noteDto)
    {
        String sessionUsername = SecurityUtil.getSessionUser();
        UserEntity sessionUser = userRepository.findByUsername(sessionUsername);

        Note note = mapToNote(noteDto);
        note.setCreatedBy(sessionUser);

        return noteRepository.save(note);
    }

    @Override
    public NoteDto findNoteById(long noteId)
    {
        Note note = noteRepository.findById(noteId).get();
        return mapToNoteDto(note);
    }

    @Override
    public void updateNote(NoteDto noteDto)
    {
        String sessionUsername = SecurityUtil.getSessionUser();
        UserEntity sessionUser = userRepository.findByUsername(sessionUsername);

        Note note = mapToNote(noteDto);

        //set fields that are not covered in update form
        note.setCreatedBy(sessionUser);
        note.setCategories(noteRepository.findById(note.getId()).get().getCategories());
        note.setCreateTime(noteRepository.findById(note.getId()).get().getCreateTime());

        noteRepository.save(note);
    }

    @Override
    public void deleteNote(long noteId)
    {
        noteRepository.deleteById(noteId);
    }

    /*
        Other methods
     */
    @Override
    public List<NoteDto> searchNotes(String query)
    {
        List<Note> notes = noteRepository.querySearchNotes(query);
        return notes.stream().map(note -> mapToNoteDto(note)).collect(Collectors.toList());
    }

    @Override
    public List<NoteDto> orderNotesByCreateTime(String orderCreateTimeQuery, UserEntity sessionUser)
    {
        if(orderCreateTimeQuery.equals("asc"))
        {
            List<Note> notes = noteRepository.queryOrderCreateTimeAsc(sessionUser.getId());
            return notes.stream().map(note -> mapToNoteDto(note)).collect(Collectors.toList());
        }
        else if (orderCreateTimeQuery.equals("desc"))
        {
            List<Note> notes = noteRepository.queryOrderCreateTimeDesc(sessionUser.getId());
            return notes.stream().map(note -> mapToNoteDto(note)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public void assignCategoriesToNote(NoteDto noteDto)
    {

        // Creating an array for fetching categories details based on their id
        List<Category> markedCategoriesList = new ArrayList<>();

        for (CategoryDto categoryDto : noteDto.getCategories())
        {
            markedCategoriesList.add(categoryRepository.findById(categoryDto.getId()).get());
        }

        Note note = noteRepository.findById(noteDto.getId()).get();
        note.setCategories(markedCategoriesList);

        noteRepository.save(note);
    }
}
