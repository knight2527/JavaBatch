package jp.co.intage.batch.task;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.TimerTask;

public class TestTask extends TimerTask {

	private static final Integer[] EXECUTE_MINUTE_ARRAY = { 0, 30 };

	@Override
	public void run() {

		// Calendar calendar = Calendar.getInstance();
		//
		// calendar.setTime(new Date());
		// int nowMinute = calendar.get(Calendar.MINUTE);
		//
		// for (int min : EXECUTE_MINUTE_ARRAY) {
		// if (min != nowMinute){
		// return;
		// }
		// }

		if (BatchManager.isShutdown()) {
			System.out.println("shutdown:" + BatchManager.getService().isShutdown());
			return;
		}

		System.out.println("batch running...");

		BatchManager.addTask(new Runnable() {

			@Override
			public void run() {
				try {

					System.out.println(Thread.currentThread().getName());

					URL getUrl = new URL("http://localhost:8080/JavaBatch/batchCheck");
					HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

					// メソッド：GET
					connection.setRequestMethod("GET");
					connection.connect();
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String lines;
					String jsonStr = "";
					while ((lines = reader.readLine()) != null) {
						jsonStr = lines;
					}
					reader.close();
					connection.disconnect();

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (ProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
	}
	
    public static void bytesCopy(byte from[], byte to[], int fromStart, int fromEnd, int toStart) {
        int count = 0;
        for (int temp = fromStart; temp < fromEnd; temp++) {
            to[toStart + count] = from[temp];
            count++;
        }
    }
    
    public static int byteArrayToInt(byte[] b, int size) 
    {
        int value = 0;
        for (int i = 0; i < size; i++) {
            int shift = (size - 1 - i) * 8;
            value += (b[i] & 0x000000FF) << shift;
        }
        return value;
    }

	public static void main(String[] args) {

		try {
			DataInputStream in = new DataInputStream(new FileInputStream(
					"D:\\work\\CM\\CM0202040112.NJI"));

			// InputStreamReader in = new InputStreamReader(new
			// FileInputStream("D:\\work\\CM\\CM0202040112.NJI"), "SJIS");

			byte array[] = new byte[108];
			int n;
			while ((n = in.read(array)) > 0) {
				byte to[] = new byte[1];
				bytesCopy(array, to, 23, 24, 0);
				
				System.out.println(byteArrayToInt(to, 1));
//				System.out.println(to[0]);
//				System.out.println(Integer.toHexString());
//				System.out.println(new String(to, "SJIS"));
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
