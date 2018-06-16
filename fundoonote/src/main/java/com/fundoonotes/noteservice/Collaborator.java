package com.fundoonotes.noteservice;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fundoonotes.userservice.User;

@Entity
public class Collaborator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int collaboratorId;

	@ManyToOne
	@JoinColumn(name = "ownerId")
	private User owner;

	@ManyToOne
	@JoinColumn(name = "sharedUserId")
	private User sharedUser;

	@ManyToOne
	@JoinColumn(name = "noteId")
	private Note note;

	public int getCollaboratorId() {
		return collaboratorId;
	}

	public void setCollaboratorId(int collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getSharedUser() {
		return sharedUser;
	}

	public void setSharedUser(User sharedUser) {
		this.sharedUser = sharedUser;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
}
