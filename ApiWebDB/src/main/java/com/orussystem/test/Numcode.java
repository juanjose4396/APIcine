package com.orussystem.test;

import com.framework.util.EmailSenderService;
import com.framework.util.UtilString;

public class Numcode {

	public static void main(String[] args) {

		EmailSenderService emailSenderService = new EmailSenderService();

		System.out.println(emailSenderService.generateOTP());

		System.out.println(UtilString.paddindRea("56", 4));
	}

}
