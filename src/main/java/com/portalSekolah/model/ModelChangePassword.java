package com.portalSekolah.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

@AutoValue
@JsonDeserialize(builder = AutoValue_ModelChangePassword.Builder.class)
public abstract class ModelChangePassword implements AbstractModel {

    public abstract String userUuid();

    public abstract String lastPassword();

    public abstract String newPassword();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public static interface Builder {

        public abstract ModelChangePassword build();

        public abstract Builder userUuid(String value);

        public abstract Builder lastPassword(String value);

        public abstract Builder newPassword(String value);

    }
}
