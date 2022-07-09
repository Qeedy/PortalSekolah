package com.portalSekolah.model;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = ModelAuth.Builder.class)
public abstract class ModelAuth {
	@Nullable
	public abstract String userName();

	@Nullable
	public abstract String password();

	public static Builder builder() {
		return new AutoValue_ModelAuth.Builder();
	}

	@AutoValue.Builder
	@JsonPOJOBuilder(withPrefix = "")
	public static interface Builder {
		public abstract ModelAuth build();

		public abstract Builder userName(String value);

		public abstract Builder password(String value);
	}
}
