package com.fundoonotes.utility;

import java.io.Serializable;

import com.fundoonotes.userservice.User;

public class UserNotedDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;

	private String userName;

	private byte[] userImage;
	
    private boolean isActive;

	private long noteCount;

	private long imageNoteCount;

	private long textNoteCount;

	public UserNotedDto() {
	}

	public UserNotedDto(int userId, String userName, byte[] userImage, boolean isActive, long noteCount,
			long imageNoteCount, long textNoteCount) {
		this.userId = userId;
		this.userName = userName;
		this.userImage = userImage;
		this.isActive = isActive;
		this.noteCount = noteCount;
		this.imageNoteCount = imageNoteCount;
		this.textNoteCount = textNoteCount;
	}

	public UserNotedDto(User user) {
		this.userId = user.getUserId();
		this.userName = user.getName();
		this.userImage = user.getUserProfilePic();
		this.isActive = user.isActive();
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public long getNoteCount() {
		return noteCount;
	}

	public void setNoteCount(long noteCount) {
		this.noteCount = noteCount;
	}

	public long getImageNoteCount() {
		return imageNoteCount;
	}

	public void setImageNoteCount(long imageNoteCount) {
		this.imageNoteCount = imageNoteCount;
	}

	public long getTextNoteCount() {
		return textNoteCount;
	}

	public void setTextNoteCount(long textNoteCount) {
		this.textNoteCount = textNoteCount;
	}
	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

}
