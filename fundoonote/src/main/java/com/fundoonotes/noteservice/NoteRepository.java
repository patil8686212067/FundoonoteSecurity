package com.fundoonotes.noteservice;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fundoonotes.userservice.User;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("from Note where user = :user")
	List<Note> findNotesByUserId(@Param("user") User user);
	
	//admin related
	//get total note count
	Long countByUser(User user);
	
	@Query("SELECT COUNT(noteImage) FROM Note u WHERE u.user=:user")
	long getImageNoteCount(@Param("user") User user);
	
	
	
}