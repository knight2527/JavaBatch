package jp.co.intage.batch.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.intage.batch.task.BatchManager;

public class BatchStopServlet extends BaseServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5229405537631676315L;


	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		BatchManager.shutdown();
	}

}
