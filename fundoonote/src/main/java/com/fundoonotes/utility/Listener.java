package com.fundoonotes.utility;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
	//listen message sending and receiving from FundooEmailQueue
	
	@JmsListener(destination="FundooEmailQueue")
	public void consume(Email email) {
		System.out.println(email);
	 }

}
