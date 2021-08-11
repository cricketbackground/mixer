package com.async.mixer.mixer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {

    //Spring boot style response params
    @JsonIgnore //see getStatus()
    @Builder.Default
    protected HttpStatus httpStatus = HttpStatus.OK;

    @Builder.Default
    protected String message = "success";

    @JsonProperty
    public int getStatus() { //return status code instead of enum name
        return this.httpStatus.value();
    }

}