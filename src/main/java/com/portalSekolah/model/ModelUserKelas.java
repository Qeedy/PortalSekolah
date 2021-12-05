package com.portalSekolah.model;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize
public abstract class ModelUserKelas implements AbstractModel{

	@Nullable
	public abstract String namaLengkap();

	@Nullable
	public abstract String statusUser();

	@Nullable
	public abstract String noTelp();

	@AutoValue.Builder
	@JsonPOJOBuilder
	public static interface Builder {
		public abstract ModelUserKelas build();

		public abstract Builder namaLengkap(String value);

		public abstract Builder statusUser(String value);

		public abstract Builder noTelp(String value);
	}
}
