package com.fundoonotes.noteservice;


import java.util.Date;

public class NoteResDto {

	private int noteId;

	private String title;

	private String description;

	private Boolean inTrash = false;

	private Boolean isArchive = false;

	private Boolean isPin = false;
	
	private Date reminder;
	
	private String color;
	
	private byte[]noteImage;

	public NoteResDto(Note note) {
		this.noteId = note.getNoteId();
		this.title=note.getTitle();
		this.description=note.getDescription();
		this.inTrash = note.getInTrash();
		this.isPin=note.getIsPin();
		this.isArchive=note.getIsArchive();
		this.reminder=note.getReminder();
		this.noteImage = note.getNoteImage();
		this.color=note.getColor();
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getInTrash() {
		return inTrash;
	}

	public void setInTrash(Boolean inTrash) {
		this.inTrash = inTrash;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public Boolean getIsPin() {
		return isPin;
	}

	public void setIsPin(Boolean isPin) {
		this.isPin = isPin;
	}
	
	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public byte[] getNoteImage() {
		return noteImage;
	}

	public void setNoteImage(byte[] noteImage) {
		this.noteImage = noteImage;
	}

	
	
}
