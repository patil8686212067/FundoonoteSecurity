package com.fundoonotes.noteservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserServiceImpl;

@Service
public class LabelServiceImpl implements ILabelService {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private LabelRepository labelRepository;
	
	@Override
	public void createLabel(Label label, int userId) {
		
		User user = userService.getUserById(userId);
		label.setUser(user);
		labelRepository.save(label);
		
	}

	@Override
	public void deleteLabel(int labelId) {
		
		labelRepository.deleteById(labelId);
		
	}

	@Override
	public List<LabelResDto> getLabels(int userId) {
		User user = userService.getUserById(userId);
		List<Label> labels = labelRepository.findLabelsByUserId(user);
		List<LabelResDto> list = new ArrayList<>();
		
		for(Label label : labels) {
			
			LabelResDto obj = new LabelResDto(label);
			list.add(obj);
		}
 		return list;
	}

	@Override
	public void updateLabel(Label label, int userId) {
		
		User user = userService.getUserById(userId);
		label.setUser(user);
		labelRepository.save(label);		
	}
	
	

}
