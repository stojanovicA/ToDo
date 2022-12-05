package com.example.ToDo.dto;

import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserDTOEdit {
	
	@JsonProperty(access=Access.READ_ONLY)
	private int id;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	private String oldPassword;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	private String newPassword;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	private String newPassword2;
	
	  @AssertTrue
	  private boolean arePasswordsEqual() {
	    return newPassword.equals(newPassword2);
	  }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the newPassword2
	 */
	public String getNewPassword2() {
		return newPassword2;
	}

	/**
	 * @param newPassword2 the newPassword2 to set
	 */
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public UserDTOEdit(int id, String email, String firstName, String lastName, String oldPassword, String newPassword,
			String newPassword2) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPassword2 = newPassword2;
	}

	public UserDTOEdit() {
		super();
	}
	
	
	
    
	
}

	
	
	
	
	
	



	
