package com.kcs.jenkinsdemo.core;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kcs.jenkinsdemo.config.AuditLogEntityListener;
import com.kcs.jenkinsdemo.enums.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user")
@EntityListeners(AuditLogEntityListener.class)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name =  "user_role")
	@Enumerated(STRING)
	private UserRole userRole;
	
	@Column(name="status")
	private Boolean status;	
}
