package com.portalSekolah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings(value = "all")
public class UserKelas {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String idUserKelas;
	@ManyToOne
	@JoinColumn(name = "id_kelas")
	private Kelas kelas;
	@OneToOne
	@JoinColumn(name = "id_siswa")
	private Siswa siswa;
	@Column
	private String namaLengkap;
	@Column
	private String statusUser;
	@Column
	private String noTelp;
	
}
