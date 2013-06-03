package com.jd.doraemon.core.snapshot;

import java.io.File; 
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

public abstract class FileSnapshotUtils {
	static final IOFileFilter filefilter = new SnapshotIOFileFilter();
	public static Map<String, Snapshot> getSnapshots(String directoryPath) {
		File directory=new File(directoryPath);
		if(!directory.exists()){
			directory.mkdirs();
		}
		Collection<File> files = FileUtils.listFiles(directory, filefilter,
				null);
		Map<String, Snapshot> snapshotsMap = new HashMap<String, Snapshot>();
		if (files == null) {
			return snapshotsMap;
		}
		for (File file : files) {
			String groupName = getGroupName(file);
			FileSnapshot snapshot = new FileSnapshot(directory, groupName);
			snapshotsMap.put(groupName, snapshot);
		}
		return snapshotsMap;
	}
	public static Map<String, Snapshot> getSnapshots(File directory) {
		Collection<File> files = FileUtils.listFiles(directory, filefilter,
				null);
		Map<String, Snapshot> snapshotsMap = new HashMap<String, Snapshot>();
		if (files == null) {
			return snapshotsMap;
		}
		for (File file : files) {
			String groupName = getGroupName(file);
			FileSnapshot snapshot = new FileSnapshot(directory, groupName);
			snapshotsMap.put(groupName, snapshot);
		}
		return snapshotsMap;
	}
	
	public static Collection<File> getSnapshotFiles(File directory) {
		Collection<File> files = FileUtils.listFiles(directory, filefilter,
				null); 
		return files;
	}

	/**
	 * GROUP_
	 * 
	 * @param file
	 * @return
	 */
	public static String getGroupName(File file) {
		String fileName = file.getName();
		String groupName = fileName.split("_")[0];
		return groupName;
	}

	static class SnapshotIOFileFilter implements IOFileFilter {

		@Override
		public boolean accept(File file) {
			String fileName = file.getName();
			return fileName.endsWith(Snapshot.SNAPSHOT_SUFFIX);
		}

		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
