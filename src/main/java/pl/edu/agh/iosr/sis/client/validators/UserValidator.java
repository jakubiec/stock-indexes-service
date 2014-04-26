package pl.edu.agh.iosr.sis.client.validators;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.edu.agh.iosr.sis.client.commands.UserCommand;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserCommand.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserCommand user = (UserCommand) target;

		String login = user.getLogin();
		if ( login != null && (login.length() < 3 || login.length() > 20) ) {
			errors.rejectValue("login", "login.wrong.length", "Username must be between 3 and 20 characters long");
		}
		if ( !StringUtils.isAlphanumeric(login) ) {
			errors.rejectValue("login", "login.wrong.chars", "Username must be alphanumeric with no spaces");
		}

		String password = user.getPassword();
		if ( user.getIsNew() ) {
			if ( password != null && (password.length() < 4 || password.length() > 20) ) {
				errors.rejectValue("password", "password.wrong.length", "The password must be between 4 and 20 characters long");
			}
		} else {
			if ( StringUtils.isNotBlank(password) ) {
				if ( password.length() < 4 || password.length() > 20 ) {
					errors.rejectValue("password", "password.wrong.length",
							"The password must be between 4 and 20 characters long");
				}
			}
		}
	}

}
