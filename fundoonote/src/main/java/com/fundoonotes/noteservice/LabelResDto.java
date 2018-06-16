package com.fundoonotes.noteservice;

public class LabelResDto {

	private int labeld;
	private String labelTitle;

	public LabelResDto(Label label) {

		this.labeld = label.getLabeld();
		this.labelTitle = label.getLabelTitle();
	}

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
}
