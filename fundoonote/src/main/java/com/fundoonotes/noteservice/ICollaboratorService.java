package com.fundoonotes.noteservice;

public interface ICollaboratorService {

//	void createCollaborator(String sharedUseremail, int noteId, int userId);
   
   void createCollaborator(CollaboratorDto collaboratorDto, int id);

	void removeCollaborator(String sharedUseremail, int noteId);
}
