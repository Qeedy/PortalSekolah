package com.portalSekolah.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
setterVisibility = JsonAutoDetect.Visibility.NONE)
public interface AbstractModel {

}
