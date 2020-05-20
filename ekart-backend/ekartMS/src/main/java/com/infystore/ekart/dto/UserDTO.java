package com.infystore.ekart.dto;

import com.infystore.ekart.document.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@ToString
@Builder
public class UserDTO {
	
	String username;
	String email;
	

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerDTO [name=" + username + ", email=" + email + "]";
	}

	// Converts Entity into DTO
	public static UserDTO valueOf(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(user.getEmail());
		userDTO.setName(user.getUsername());
		return userDTO;
	}

	// Converts DTO into Entity
	public User createEntity() {
		User user = new User();
		user.setEmail(this.getEmail());
		user.setUsername(this.getName());
		return user;
	}

}
