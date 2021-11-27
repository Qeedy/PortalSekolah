package com.portalSekolah.entity;

import java.sql.Date;
import java.util.List;

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
@SuppressWarnings(value="all")
public class MataPelajaran {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String uuid;
	@ManyToOne
	@JoinColumn(name = "id_kelas")
	private Kelas kelas;
	@OneToOne
	@JoinColumn(name = "id_guru")
	private Guru guru;
	@Column
	private String namaKelas;
	@Column
	private String namaGuru;
	@OneToMany(mappedBy = "mataPelajaran")
	private List<UserMataPelajaran> users;
	@Column
	private Date jadwalMataPelajaran;
}
