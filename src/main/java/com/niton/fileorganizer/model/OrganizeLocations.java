package com.niton.fileorganizer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.niton.media.filesystem.Directory;

public class OrganizeLocations  implements Serializable {
	private List<Directory> sources = new ArrayList<>();
	private Directory target = new Directory("D:\\New System");
	public boolean addSource(Directory e) {
		return sources.add(e);
	}
	public boolean removeSource(Directory e) {
		return sources.remove(e);
	}
	public Directory getTarget() {
		return target;
	}
	public void setTarget(Directory target) {
		this.target = target;
	}
	public void setSources(List<Directory> sources) {
		this.sources = sources;
	}
	public void addSources(List<Directory> dirs) {
		sources.addAll(dirs);
	}
	public List<Directory> getSources() {
		return sources;
	}
}
