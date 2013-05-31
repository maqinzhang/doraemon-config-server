/**
 * 
 */
package com.jd.doraemon.server.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import com.jd.doraemon.core.cluster.GroupClusters;
import com.jd.doraemon.core.snapshot.FileSnapshotUtils;
import com.jd.doraemon.core.snapshot.Snapshot;
import com.jd.doraemon.server.BaseContainer;

/**
 * @author luolishu
 *
 */
public abstract class AbstractServerContainer extends BaseContainer {
	protected String directoryPath; 
	protected String serverName;
	protected long initialDelay;
	protected long synPeriodTime;
	protected ThreadPoolExecutor threadPoolExecutor;
	protected Map<String, Snapshot> snapshotMap = new HashMap<String, Snapshot>();
	protected Map<String,GroupClusters> groupClustersMap=new HashMap<String,GroupClusters>();

	/* (non-Javadoc)
	 * @see com.jd.doraemon.server.ServerContainer#getThreadPoolExecutor()
	 */
	@Override
	public ThreadPoolExecutor getThreadPoolExecutor() {
		// TODO Auto-generated method stub
		return null;
	}

 
	@Override
	public Snapshot getSnapshot(String group) { 
		return snapshotMap.get(group);
	}

	 
	@Override
	public void startup(Properties properties) {
		// TODO Auto-generated method stub

	}

	 
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

 
	@Override
	public void reloadLocal() {
		Map<String, Snapshot> snapshotsMap=FileSnapshotUtils.getSnapshots(directoryPath);
		Set<Entry<String, Snapshot>> entrySet=snapshotsMap.entrySet();
		for(Entry<String, Snapshot> entry:entrySet){
			String key=entry.getKey();
			Snapshot snapshot=entry.getValue();
			snapshot.compareAndflush();
			snapshot.load();
		}
		

	}

	/* (non-Javadoc)
	 * @see com.jd.doraemon.server.ServerContainer#pushAllToClients()
	 */
	@Override
	public void pushAllToClients() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.jd.doraemon.server.ServerContainer#pushToClients(java.lang.String)
	 */
	@Override
	public void pushToClients(String group) {
		// TODO Auto-generated method stub

	}

}
