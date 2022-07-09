package com.portalSekolah.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_ModelUserRegistration.Builder.class)
public abstract class ModelUserRegistration implements AbstractModel {

	public abstract String username();
	public abstract String password();	
	public abstract String firstName();
	public abstract String lastName();
	@JsonSerialize(using = LocalDateSerializer.class)
	public abstract LocalDate birthDay();
	public abstract String userType();
	public abstract String emailAddress();
	public abstract String phoneNumber();
	public abstract String address();
	public abstract String gender();
	public abstract List<String> roles();
	
	@AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
	public static interface Builder {
		public abstract ModelUserRegistration build();
		public abstract Builder username(String value);
		public abstract Builder password(String value);
		public abstract Builder firstName(String value);
		public abstract Builder lastName(String value);
		public abstract Builder birthDay(LocalDate value);
		public abstract Builder userType(String value);
		public abstract Builder emailAddress(String value);
		public abstract Builder phoneNumber(String value);
		public abstract Builder address(String value);
		public abstract Builder gender(String value);
		public abstract Builder roles(List<String> value);
	}
}
