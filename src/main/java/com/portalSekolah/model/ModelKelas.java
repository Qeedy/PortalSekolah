package com.portalSekolah.model;

import java.util.List;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_ModelKelas.Builder.class)
public abstract class ModelKelas implements AbstractModel{

	@Nullable
	public abstract String namaKelas();
	@Nullable
	public abstract String idGuru();
	@Nullable
	public abstract String namaWaliKelas();
	@Nullable
	public abstract List<ModelUserKelas> users();
	
	public static Builder builder() {
        return new AutoValue_ModelKelas.Builder();
    }

	@AutoValue.Builder
	@JsonPOJOBuilder(withPrefix = "")
	public static interface Builder {
		public abstract ModelKelas build();

		public abstract Builder namaKelas(String value);

		public abstract Builder idGuru(String value);
		
		public abstract Builder namaWaliKelas(String value);
		
		public abstract Builder users(List<ModelUserKelas> value);
	}
}
