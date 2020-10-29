package com.niton.fileorganizer.model;

import com.niton.media.filesystem.NFile;

import java.io.Serializable;
import java.util.Comparator;

public class NFileComparator implements Comparator<NFile>, Serializable {
	@Override
	public int compare(NFile o1, NFile o2) {
		return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
	}
}
