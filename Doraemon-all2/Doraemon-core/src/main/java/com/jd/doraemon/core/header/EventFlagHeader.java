package com.jd.doraemon.core.header;

import java.io.DataInput;
import java.io.DataOutput;

import org.jgroups.Global;
import org.jgroups.Header;

public class EventFlagHeader extends Header {
	public static final short HEADER_ID = 10000;
	int type;

	public EventFlagHeader(int type) {
		this.type = type;
	}

	@Override
	public void writeTo(DataOutput out) throws Exception {
		out.writeInt(type);
	}

	@Override
	public void readFrom(DataInput in) throws Exception {
		type = in.readInt();
	}

	public int getType() {
		return type;
	}
 
	@Override
	public int size() {
		return Global.INT_SIZE;
	}

}
