package com.portalSekolah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings(value = "all")
public class Guru {

	@Id
	private String uuid;
	@Column
	private String namaLengkap;
	@Column
	private String gender;
	@Column
	private String emailAddress;
	@Column
	private String noTelp;

}
