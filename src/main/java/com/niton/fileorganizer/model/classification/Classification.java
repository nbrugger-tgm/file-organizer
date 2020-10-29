package com.niton.fileorganizer.model.classification;

import com.niton.fileorganizer.controller.ClassificationController;
import com.niton.fileorganizer.controller.classification.ClassificationEditorController;
import com.niton.media.filesystem.NFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public abstract class Classification<T extends ClassificationType>  implements Serializable {
	private final T type;
	private String name;

	public Classification(T type) {
		this.type = type;
	}


	private Map<String,Double> possibilities = new HashMap<>();

	public void classify(NFile file) throws IOException {
		possibilities.clear();
		computePossibilities(file);
	}

	/**
	 * use {@link #addPossibility(String, double)} to add a possible result
	 * @param file
	 * @throws IOException
	 */
	public abstract void computePossibilities(NFile file) throws IOException;

	protected void addPossibility(String result,double certainty){
		possibilities.put(result,certainty);
	}

	public Map<String, Double> getPossibilitieMap() {
		return possibilities;
	}
	public Set<String> getPossibilities() {
		return possibilities.keySet();
	}
	public double getCertainity(String result){
		return possibilities.get(result);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getType() {
		return type;
	}

	public ClassificationEditorController<? extends Classification<T>> createController(ClassificationController superController){
		return getType().createController(this,superController);
	}
	@Override
	public String toString() {
		return getName();
	}

	public String getMostLikely(){
		double likely = 0;
		String val = null;
		for (Map.Entry<java.lang.String, Double> entry : possibilities.entrySet()) {
			if(entry.getValue() > likely) {
				val = (String) entry.getKey();
				likely = entry.getValue();
			}
		}
		return val;
	}
}
