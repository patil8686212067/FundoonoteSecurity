package com.fundoonotes.noteservice;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface INoteService {

	int createNote(Note note, int id);
	int updateNote(Note note, int userId);
	void deleteNote(int id);
	List<NoteResDto> getNotes(int id);	
	void saveImage(MultipartFile fileUpload, int noteId) throws IOException;
	Note getNoteByNoteId(int id);
	void deleteImage(int noteId);
}
