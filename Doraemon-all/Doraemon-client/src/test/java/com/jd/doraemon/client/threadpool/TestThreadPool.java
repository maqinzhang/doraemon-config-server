/**
 * 
 */
package com.jd.doraemon.client.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luolishu
 * 
 */
public class TestThreadPool {
	static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final AtomicInteger flag = new AtomicInteger(0);
		for (int i = 0; i < 5; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					while (true) {
						flag.incrementAndGet();
						System.out.println(Thread.currentThread());
						try {
							Thread.sleep(1000L);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (flag.intValue() == 100) {
							synchronized (flag) {
								if (flag.intValue() == 100) {
									System.out.println("break thread="
											+ Thread.currentThread());
									flag.incrementAndGet();
									break;
								}
							}
						}
					}

				}
			});
		}

		executor.submit(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out
							.println("++++++++++++++++++++++++++++++++++++++++++");
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

	}

}
