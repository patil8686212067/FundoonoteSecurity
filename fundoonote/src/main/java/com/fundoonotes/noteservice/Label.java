package com.fundoonotes.noteservice;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fundoonotes.userservice.User;

@Entity
@Table(name="Label")
public class Label {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int labeld;
	
	@Column
	private String labelTitle;

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public int getLabeld() {
		return labeld;
	}

	public void setLabeld(int labeld) {
		this.labeld = labeld;
	}

	public String getLabelTitle() {
		return labelTitle;
	}

	public void setLabelTitle(String labelTitle) {
		this.labelTitle = labelTitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
