package com.ad.realestateengine.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.ad.realestateengine.security.rules.SecurityRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "ad_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String lastName;

	private String username;

	@NotBlank
	private String email;

	@JsonIgnore
	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "roles")
	private List<SecurityRoles> roles = new ArrayList<SecurityRoles>();;

	public void addRole(SecurityRoles role) {
		if (this.roles.stream().noneMatch(r -> r.name().equals(role.name()))) {
			this.roles.add(role);
		}
	}

	public void removeRole(SecurityRoles role) {
		if (this.roles != null && this.roles.contains(role)) {
			this.roles.remove(role);
		}
	}
}
