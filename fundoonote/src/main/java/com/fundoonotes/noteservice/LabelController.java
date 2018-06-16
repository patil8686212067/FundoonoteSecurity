package com.fundoonotes.noteservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotes.utility.TokenUtils;

@RestController
public class LabelController {

	@Autowired
	ILabelService labelService;
	
	@RequestMapping(value="createlabel", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Label> createLabel(@RequestBody Label label, HttpServletRequest request,
			@RequestHeader("Authorization") String token){

		int userId = TokenUtils.verifyToken(token);
		labelService.createLabel(label, userId);
		return new ResponseEntity<Label>(label, HttpStatus.OK);

	}
	
	@RequestMapping(value="getlabels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLabels(HttpServletRequest request,
			@RequestHeader("Authorization") String token){
	
		System.out.println("In side get label controller...");
		int userId = TokenUtils.verifyToken(token);
		
		List<LabelResDto> label= labelService.getLabels(userId);
		
		return new ResponseEntity<List>(label, HttpStatus.OK);
	}
	
	@RequestMapping(value="deletelabel/{labelId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLabel(@PathVariable("labelId") int labelId,HttpServletRequest request){
		
		try {
			labelService.deleteLabel(labelId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/updatelabel", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateLabel(@RequestBody Label label, HttpServletRequest request,
			@RequestHeader("Authorization") String token) {
		int userId = TokenUtils.verifyToken(token);
		try {
			labelService.updateLabel(label, userId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
}

