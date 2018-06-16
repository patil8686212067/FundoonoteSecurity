package com.fundoonotes.noteservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fundoonotes.userservice.User;

@Repository(value="collaboratorRepository")
public interface CollaboratorRepository extends JpaRepository<Collaborator, Integer> {

	
	@Query("delete from Collaborator c where c.note = :note and c.sharedUser = :user")
	void removeCollaboratorFromNote(@Param("user") User user, @Param("note") Note note);
}
