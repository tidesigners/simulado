package br.com.tiupgrade.simulado.infra;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ScheduleFactory {

    @PostConstruct
    public void start() {
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler sched = sf.getScheduler();

            JobKey job1Key = JobKey.jobKey("job1", "my-jobs");
            JobDetail job1 = JobBuilder
                    .newJob(Schedule.class)
                    .withIdentity(job1Key)
                    .build();

            TriggerKey tk1 = TriggerKey.triggerKey("trigger1", "my-jobs");
            Trigger trigger1 = TriggerBuilder
                    .newTrigger()
                    .withIdentity(tk1)
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(0))
                    .build();

            sched.start(); // start before scheduling jobs
            sched.scheduleJob(job1, trigger1);

        } catch (SchedulerException e) {
            System.out.println(e.getMessage());
        }
    }
}
