package com.fundoonotes.userservice;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	  public void register(UserDto userDto, String requestURL);
	  public String login(UserDto userDto);
	  public User getUserById(int userId);
	  int userActivation(String randomId);
	  public boolean forgetPassword(String email, String url);
	  public int resetPassword(UserDto userDto);
	  void uploadImage(MultipartFile uploadProfileImage, int userId) throws IOException;
}
