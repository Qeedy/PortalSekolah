package com.portalSekolah.entity;

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
@SuppressWarnings(value = "all")
public class Kelas {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String uuid;
	@Column
	private String namaKelas;
	@ManyToOne
	@JoinColumn(name = "id_guru")
	private Guru guru;
	@Column
	private String namaWaliKelas;
	@OneToMany(mappedBy = "kelas")
	private List<UserKelas> users;
	
	public void addUserKelas(UserKelas userKelas) {
		userKelas.setKelas(this);
		this.users.add(userKelas);
	}

}
