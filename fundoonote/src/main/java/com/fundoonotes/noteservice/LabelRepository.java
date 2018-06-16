package com.fundoonotes.noteservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fundoonotes.userservice.User;

@Repository(value="labelRepository")
public interface LabelRepository extends JpaRepository<Label, Integer> {

	@Query("from Label where user = :user")
	List<Label> findLabelsByUserId(@Param("user") User user);
	
	
}
