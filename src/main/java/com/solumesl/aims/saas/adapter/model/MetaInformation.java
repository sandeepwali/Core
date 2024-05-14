package com.solumesl.aims.saas.adapter.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MetaInformation {
    private String articleId;
    private String articleName;
    private String nfcUrl;
    @Singular
    private List<String> eans;
    @JsonProperty("data")
    private Object data;
}
