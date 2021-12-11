package com.portalSekolah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings(value = "all")
public class Siswa {

	@Id
	private String uuid;
	@Column
	private String namaLengkap;
	@Column
	private String gender;
	@Column
	private String alamat;
	@Column
	private String emailAddress;
	@Column
	private String noTelp;

}
