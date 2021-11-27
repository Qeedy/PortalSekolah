package com.portalSekolah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuppressWarnings(value="all")
public class JawabanSoal {

	@ManyToOne
	@JoinColumn(name="id_tugas")
	private Tugas tugas;
	@OneToOne
	@JoinColumn(name = "id_soal")
	private Soal idSoal;
	@OneToOne(mappedBy = "siswa")
	@JoinColumn(name = "id_siswa")
	private UserMataPelajaran user;
	@Column
	private String jawaban;
	@Column
	private String nilai;
	
}
