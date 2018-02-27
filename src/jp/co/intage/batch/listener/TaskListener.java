package jp.co.intage.batch.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.co.intage.batch.task.BatchManager;
import jp.co.intage.batch.task.TestTask;

public class TaskListener implements ServletContextListener {

	private Timer timer;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if (timer != null) {
			timer.cancel();
		}
		
		BatchManager.shutdown();
	}
	
	

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		timer = new Timer("test task", true);
		timer.schedule(new TestTask(), 0, 3000);
	}

}
