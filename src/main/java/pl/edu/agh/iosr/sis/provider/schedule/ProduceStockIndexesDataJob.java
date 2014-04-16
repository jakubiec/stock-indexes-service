package pl.edu.agh.iosr.sis.provider.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ProduceStockIndexesDataJob extends QuartzJobBean {

	private ProduceStockIndexesDataTask task;

	public void setTask(ProduceStockIndexesDataTask task) {
		this.task = task;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		task.produce();
	}

}
