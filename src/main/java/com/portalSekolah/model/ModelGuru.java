package com.portalSekolah.model;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = ModelGuru.Builder.class)
public abstract class ModelGuru {

	@Nullable
	public abstract String uuid();

	@Nullable
	public abstract String namaLengkap();

	@Nullable
	public abstract String gender();

	@Nullable
	public abstract String alamat();

	@Nullable
	public abstract String emailAddress();

	@Nullable
	public abstract String noTelp();
	
	public static Builder builder() {
        return new AutoValue_ModelGuru.Builder();
    }

	@AutoValue.Builder
	@JsonPOJOBuilder(withPrefix = "")
	public static interface Builder {
		public abstract ModelGuru build();

		public abstract Builder uuid(String value);

		public abstract Builder namaLengkap(String value);

		public abstract Builder gender(String value);

		public abstract Builder alamat(String value);

		public abstract Builder emailAddress(String value);

		public abstract Builder noTelp(String value);

	}
}
