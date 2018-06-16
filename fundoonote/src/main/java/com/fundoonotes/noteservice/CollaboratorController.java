package com.fundoonotes.noteservice;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.utility.TokenUtils;

@RestController
public class CollaboratorController {

   @Autowired
   ICollaboratorService collaboratorService;
   
   @RequestMapping(value = "/addcollaborator", method = RequestMethod.PUT)
   public ResponseEntity<Void> addCollaborator(@RequestBody CollaboratorDto collaboratorDto, @RequestHeader("Authorization") String token) {
   
      System.out.println("Inside add collaborator");
      int id = TokenUtils.verifyToken(token);
      try {
         collaboratorService.createCollaborator(collaboratorDto, id);
         return new ResponseEntity<Void>(HttpStatus.OK);
      } catch (Exception e) {
         e.printStackTrace();
         return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      }
   }
   
   @RequestMapping(value = "/removecollaborator", method = RequestMethod.DELETE)
   public ResponseEntity<Void> removeCollaborator(@RequestParam String sharedUserEmail, @RequestParam int noteId){
      
      try {
         collaboratorService.removeCollaborator(sharedUserEmail, noteId);
         return new ResponseEntity<Void>(HttpStatus.OK);
      } catch (Exception e) {
         e.printStackTrace();
         return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      }
   }
   
}