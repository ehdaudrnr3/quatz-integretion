package io.batch.schdul.batch.dto;

import io.batch.schdul.batch.domain.BatchJob;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.BatchStatus;

@Getter @Setter
public class BatchJobDto {
    private String jobId;
    private String jobName;

    public BatchJobDto(String jobId, String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    public BatchJob toEntity() {
        BatchJob batchJob = new BatchJob();
        batchJob.setId(this.jobId);
        batchJob.setName(this.jobName);
        batchJob.setBatchStatus(BatchStatus.STOPPED);

        return batchJob;
    }
}
