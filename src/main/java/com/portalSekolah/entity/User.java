package com.portalSekolah.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings(value = "all")
public class User implements Serializable {

	private static final long serialVersionUID = -2897532802294601009L;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String uuid;
	@Column(unique = true)
	private String username;
	@Column
	private String namaDepan;
	@Column
	private String namaBelakang;
	@Column
	private String gender;
	@Column
	private String emailAddress;
	@Column
	private String alamat;
	@Column
	private String noTelp;

}
