package io.batch.schdul.batch.rest;

import io.batch.schdul.batch.domain.BatchJob;
import io.batch.schdul.batch.dto.BatchJobDto;
import io.batch.schdul.batch.service.BatchJobService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
public class BatchJobController {

    private final BatchJobService batchJobService;

    @PostMapping("/api/batch/job")
    public BatchJobResponse saveBatchJob(@RequestBody BatchJobDto jobDto) {
        batchJobService.save(jobDto.toEntity());
        return new BatchJobResponse(jobDto.getJobId(), jobDto.getJobName());
    }

    @GetMapping("/api/batch/job/{jobId}")
    public BatchJobResponse findJob(@PathVariable("jobId") String jobId) {
        BatchJob job = batchJobService.findById(jobId);
        return new BatchJobResponse(job.getId(), job.getName());
    }

    @GetMapping("/api/batch/run/{jobId}")
    public void run(@PathVariable("jobId") String jobId) {

        batchJobService.run(jobId);
    }

    @Data
    @AllArgsConstructor
    static class BatchJobResponse {
        private String jobId;
        private String name;
    }
}
