package org.dev.service;

import org.dev.dao.RegistrationDAO;
import org.dev.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationDAO registrationDAO;

	public boolean register(UserDTO userDTO) {
		Long identifier = registrationDAO.saveUser(userDTO);

		if (identifier != null && identifier > 0) {
			return true;
		} else {
			return false;
		}
	}
}
