package com.niton.fileorganizer.model.classification.implementation;

import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.types.ExtensionType;
import com.niton.media.filesystem.NFile;

import java.io.IOException;
import java.util.LinkedHashMap;

public class ExtensionClassification extends Classification<ExtensionType> {
	private LinkedHashMap<String, String> map = new LinkedHashMap<>();
	private boolean groupUndefined = false;
	public ExtensionClassification() {
		super(new ExtensionType());
	}

	@Override
	public void computePossibilities(NFile file) throws IOException {
		String res = map.get(file.getEnding().toLowerCase());
		addPossibility(res,1);
	}

	public LinkedHashMap<String, String> getMap() {
		return map;
	}
}
