/**
 * 
 */
package com.jd.doraemon.server.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import com.jd.doraemon.core.cluster.GroupClusters;
import com.jd.doraemon.core.snapshot.FileSnapshot;
import com.jd.doraemon.core.snapshot.FileSnapshotUtils;
import com.jd.doraemon.core.snapshot.Snapshot;
import com.jd.doraemon.server.BaseServerContainer;
import com.jd.doraemon.server.ResourceHolder;

/**
 * @author luolishu
 * 
 */
public abstract class AbstractServerContainer extends BaseServerContainer
		implements ResourceHolder {

	protected long initialDelay;
	protected long synPeriodTime;
	protected ThreadPoolExecutor threadPoolExecutor;

	protected Map<String, GroupClusters> groupClustersMap = new HashMap<String, GroupClusters>();

	@Override
	public ThreadPoolExecutor getThreadPoolExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Snapshot getSnapshot(String group) {
		Snapshot snapshot = snapshotMap.get(group);
		if (snapshot == null) {
			snapshot = new FileSnapshot(new File(this.directoryPath), group);
			snapshotMap.put(group, snapshot);
		}
		return snapshot;
	}

	@Override
	public void startup(Properties properties) {

		reloadLocal();
		resourceManager.init();
		running = true;
	}

	@Override
	public void shutdown() {
		running = false;

	}

	@Override
	public void reloadLocal() {
		Map<String, Snapshot> snapshotsMap = FileSnapshotUtils
				.getSnapshots(directoryPath);
		Set<Entry<String, Snapshot>> entrySet = snapshotsMap.entrySet();
		for (Entry<String, Snapshot> entry : entrySet) {
			Snapshot snapshot = entry.getValue();
			snapshot.load();
			snapshotMap.put(entry.getKey(), snapshot);
		}

	}

	@Override
	public void pushAllToClients() {
		for (String group : groups) {
			this.pushToClients(group);
		}

	}

	@Override
	public void pushToClients(String group) {
		// TODO Auto-generated method stub

	}

}
