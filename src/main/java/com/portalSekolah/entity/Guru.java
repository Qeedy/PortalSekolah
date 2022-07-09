package com.portalSekolah.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Guru implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2800263755660370078L;
	@Id
	private String uuid;
	@Column
	private String namaLengkap;
	@Column
	private String gender;
	@Column(nullable = true)
	private String alamat;
	@Column
	private String emailAddress;
	@Column
	private String noTelp;

}
