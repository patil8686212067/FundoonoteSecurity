package com.fundoonotes.adminservice;

import java.util.List;

import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserDto;
import com.fundoonotes.utility.UserNotedDto;

public interface IAdminService {
	 
	public List<UserNotedDto> getUserNoteCount();
}
