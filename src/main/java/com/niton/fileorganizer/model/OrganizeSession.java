package com.niton.fileorganizer.model;

import com.niton.fileorganizer.model.classification.Classification;
import com.niton.media.filesystem.Directory;
import com.niton.media.filesystem.NFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class OrganizeSession  implements Serializable {
	private final Map<NFile, Map<String,String>>  progress = new TreeMap<>(new NFileComparator());
	private final Map<NFile,Map<String, Map<String, Double>>> calculationMap = new TreeMap<>(new NFileComparator());
	public OrganisationNode tree;
	public boolean inClassification = false;
	private OrganizeLocations locations = new OrganizeLocations();

	public OrganizeLocations getLocations() {
		return locations;
	}

	public Map<NFile, Map<String, String>> getProgress() {
		return progress;
	}

	public void addFile(NFile p) throws IOException {
		progress.put(p,new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
		progress.get(p).put(tree.getClassification().getName(),null);
		calculationMap.put(p,new TreeMap<>());
		calculatePath(p);
	}

	public void calculatePath(NFile p) throws IOException {
		OrganisationNode node = tree;
		Set<String> allowedKeys = new HashSet<>();
		while(node.getClassification() != null){
			allowedKeys.add(node.getClassification().getName());
			String value = progress.get(p).get(node.getClassification().getName());
			calculate(p,node.getClassification());
			if(value == null) {
				progress.get(p).put(node.getClassification().getName(),null);
				value = node.getClassification().getMostLikely();
			}
			OrganisationNode nextNode = node.getDecisions().get(value);
			if(nextNode == null){
				nextNode = node.getNoDecisionNode();
			}
			node = nextNode;
		}
		progress.get(p).keySet().retainAll(allowedKeys);

	}

	private void calculate(NFile p, Classification<?> classification) throws IOException {
		classification.classify(p);
		calculationMap.get(p).put(classification.getName(),classification.getPossibilitieMap());
	}

	public Map<String, Map<String, Double>> getCalculations(NFile p) {
		return calculationMap.get(p);
	}

	public void clearClassifications() {
		inClassification = false;
		calculationMap.clear();
		progress.clear();
		files.clear();
	}
	private final Stack<NFile> files = new Stack<>();
	public void clearFileStack() {
		files.clear();
	}

	public void addToStack(NFile file) {
		files.push(file);
	}

	public NFile currentFile() {
		return files.peek();
	}

	public boolean hasFiles() {
		return !files.isEmpty();
	}

	public void completeFile() {
		files.pop();
	}

	public String getNonNullValue(NFile file,String classifier){
		String text = progress.get(file).get(classifier);
		if(text != null)
			return text;
		double value = 0;
		for (Map.Entry<String, Double> biasedEntry : calculationMap.get(file).get(classifier).entrySet()) {
			if(biasedEntry.getValue() >= value) {
				value = biasedEntry.getValue();
				text = biasedEntry.getKey();
			}
		}
		return text;
	}

	public Map<String,String> getNonNullValues(NFile file){
		TreeMap<String, String> map = new TreeMap<>();
		for (Map.Entry<String, String> entry : progress.get(file).entrySet()) {
			if(entry.getValue() == null){
				map.put(entry.getKey(),getNonNullValue(file,entry.getKey()));
			}else{
				map.put(entry.getKey(),entry.getValue());
			}
		}
		return map;
	}

	public void copyFile(NFile p) throws IOException {
		Directory newParent = getLocations().getTarget();
		newParent = walkTree(newParent,tree,getNonNullValues(p));
		p.copy(new NFile(newParent,p.getFile().getName()));
	}

	private Directory walkTree(Directory newParent, OrganisationNode tree, Map<String, String> nonNullValues) throws IOException {
		String val = nonNullValues.get(tree.getClassification().getName());
		newParent = newParent.addAndSaveDir(val);
		OrganisationNode next = tree.getDecisions().get(val);
		if (next == null)
			next = tree.getNoDecisionNode();
		if(next != null && next.getClassification() != null){
			newParent = walkTree(newParent,next,nonNullValues);
		}
		return newParent;
	}
}
