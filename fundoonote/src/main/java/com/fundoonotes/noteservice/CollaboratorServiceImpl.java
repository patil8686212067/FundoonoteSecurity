package com.fundoonotes.noteservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserDAO;

@Service
public class CollaboratorServiceImpl implements ICollaboratorService {

	@Autowired
	private UserDAO userDao;
	@Autowired
	INoteService noteService;
	
	@Autowired
	CollaboratorRepository collaboratorRepository;
	
	@Override
	public void createCollaborator(CollaboratorDto collaboratorDto, int userId) {
		
		Collaborator collaborator =  new Collaborator();
		collaborator.setNote(noteService.getNoteByNoteId(collaboratorDto.getNoteId()));
		collaborator.setSharedUser(userDao.findByEmail(collaboratorDto.getEmail()));
		collaborator.setOwner(userDao.findById(userId).get());
		collaboratorRepository.save(collaborator);	
	}

	@Override
	public void removeCollaborator(String sharedUseremail, int noteId) {
		
		User user = userDao.findByEmail(sharedUseremail);
		Note note = noteService.getNoteByNoteId(noteId);
		collaboratorRepository.removeCollaboratorFromNote(user, note);
	}
}
