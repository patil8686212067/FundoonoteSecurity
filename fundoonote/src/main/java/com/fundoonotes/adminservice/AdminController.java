package com.fundoonotes.adminservice;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fundoonotes.utility.UserNotedDto;



@RestController
public class AdminController {

    @Autowired
	IAdminService adminService;
    
    /**<p>This Api is used to get users and user total number of note count,user text note count
     * and image note count
     * </p>
     * @return
     */
    @RequestMapping(value = "/admin/getusers", method = RequestMethod.GET)
    public ResponseEntity<List<UserNotedDto>> getUserNoteCount()
    {

       return new ResponseEntity<>(adminService.getUserNoteCount(), HttpStatus.OK);

    }
  
	
		
}
