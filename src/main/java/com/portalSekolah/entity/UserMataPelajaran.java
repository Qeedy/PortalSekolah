package com.portalSekolah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class UserMataPelajaran {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String uuid;
	@ManyToOne
	@JoinColumn(name = "id_mata_pelajaran")
	private MataPelajaran mataPelajaran;
	@ManyToOne
	@JoinColumn(name = "id_siswa")
	private Siswa siswa;
	@Column
	private String namaLengkap;
	@Column
	private String namaMataPelajaran;
}
