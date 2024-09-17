package br.com.drianodev.envioPromocoesClientesJob.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvioPromocoesClientesScheduleJob extends QuartzJobBean {

	@Autowired
	private Job job;

	@Autowired
	private JobExplorer jobExplorer;

	@Autowired
	private JobLauncher jobLauncher;

	private static final Logger logger = LoggerFactory.getLogger(EnvioPromocoesClientesScheduleJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobParameters jobParameters = new JobParametersBuilder(this.jobExplorer).getNextJobParameters(this.job).toJobParameters();
		try {
			this.jobLauncher.run(this.job, jobParameters);
		} catch (Exception e) {
			logger.error("Error executing job", e);
		}
	}
}
