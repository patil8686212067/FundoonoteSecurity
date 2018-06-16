package com.fundoonotes.adminservice;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fundoonotes.noteservice.NoteRepository;
import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserDto;

import com.fundoonotes.utility.TokenUtils;
import com.fundoonotes.utility.UserNotedDto;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	AdminRepositry adminRepositry;
	@Autowired
	NoteRepository noteRepository;
	private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Transactional
	@Override
	public List<UserNotedDto> getUserNoteCount() {
		List<UserNotedDto> userNoteCountList = new ArrayList<UserNotedDto>();
		
		Iterable<User> users = adminRepositry.findAll();
		for (User user : users) {
			System.out.println(user.getUserId());
		
			long noteCount =noteRepository.countByUser(user);
			System.out.println("total note of user==>"+noteCount);
			
	       long imageNoteCount = noteRepository.getImageNoteCount(user);
			System.out.println(" total imageNoteCount==>"+imageNoteCount);
			        
			UserNotedDto userNoteDto = new UserNotedDto(user);
			userNoteDto.setNoteCount(noteCount);
			userNoteDto.setImageNoteCount(imageNoteCount);
			userNoteDto.setTextNoteCount(noteCount-imageNoteCount);
			System.out.println(" total textNoteCount==>"+userNoteDto.getTextNoteCount());
			userNoteCountList.add(userNoteDto);
		}
		return userNoteCountList;
	}

}
