package jp.co.intage.batch.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BatchCheckServlet extends BaseServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5229405537631676315L;

	public static int num = 0;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int thead = ++num;
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss ");

		logger.info(format.format(new Date()) + "thead-" + thead + " BatchCheckServlet start!!!");

		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		resp.setContentType("text/html;charset=utf-8");
		resp.setStatus(HttpServletResponse.SC_OK);
		String rspContents = setResponseContents(HttpServletResponse.SC_OK, "111");
		// レスポンス内容を返す
		PrintWriter out = resp.getWriter();
		out.println(rspContents);

		logger.info(format.format(new Date()) + "thead-" + thead + " BatchCheckServlet end!!!");
	}

	/**
	 * 
	 * @param resp
	 *            : レスポンスコンテンツ
	 * @param rtMsg
	 *            : 配信結果
	 * @return レスポンス
	 * @throws IOException
	 */
	private String setResponseContents(int rspStatus, String rtMsg) throws IOException {
		StringBuffer sb = new StringBuffer();
		String br = "<br>";

		sb.append("<HTML>");
		sb.append("<BODY>");
		sb.append("respose status : " + rspStatus + br);
		sb.append("<h3>配信結果：" + rtMsg + "</h3");
		sb.append("</BODY>");
		sb.append("</HTML>");

		return sb.toString();
	}

}
