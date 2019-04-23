package com.src.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	// email id validation
	public boolean emailValidate(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			System.out.println("Incorrect email");
			return false;
		}

		return pat.matcher(email).matches();
	}

	// mobile number validation
	public boolean mobileValidate(long mobileNumber) {
		int length = String.valueOf(mobileNumber).length();
		if (length == 10) {
			return true;
		} else {
			return false;
		}
	}

	// aadhar validation
	public boolean aadharValidate(long adhar) {
		int length = String.valueOf(adhar).length();
		if (length == 12) {
			return true;
		} else {
			return false;
		}
	}

	// password validation
	public boolean password(String str) {

		if (str.length() >= 8) {
			Pattern letter = Pattern.compile("[a-zA-z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

			Matcher hasLetter = letter.matcher(str);
			Matcher hasDigit = digit.matcher(str);
			Matcher hasSpecial = special.matcher(str);

			if (hasLetter.find() && hasDigit.find() && hasSpecial.find()) {
				// utility.passwordStatus();
				return true;
			} else
				return false;
		} else
			return false;
	}

	public boolean panValidate(String pan) {
		String s = pan; // get your editext value here
		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

		Matcher matcher = pattern.matcher(s);

		// Check if pattern matches
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
}
