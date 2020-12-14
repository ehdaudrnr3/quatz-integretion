package io.batch.schdul.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class JobForm {

    @NotEmpty(message = "Job ID은 필수입니다.")
    private String jobId;
    private String jobName;
}
