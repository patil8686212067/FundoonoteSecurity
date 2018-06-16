package com.fundoonotes.noteservice;

public class CollaboratorDto
{
   private int noteId;
   private String email;
   
   public int getNoteId()
   {
      return noteId;
   }
   public void setNoteId(int noteId)
   {
      this.noteId = noteId;
   }
   public String getEmail()
   {
      return email;
   }
   public void setEmail(String email)
   {
      this.email = email;
   }
}
