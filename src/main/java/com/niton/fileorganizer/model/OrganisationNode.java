package com.niton.fileorganizer.model;

import com.niton.fileorganizer.model.classification.Classification;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrganisationNode implements Serializable {
    private Classification<?> classification;
    private LinkedHashMap<String,OrganisationNode> decisions = new LinkedHashMap<>();
    private OrganisationNode noDecisionNode = null;

    public OrganisationNode(Classification<?> classification) {
        setClassification(classification);
    }
    public OrganisationNode(){ }

    public OrganisationNode addDecision(String key, OrganisationNode value) {
        return decisions.put(key, value);
    }

    public Classification<?> getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
        if(noDecisionNode == null){
            noDecisionNode = new OrganisationNode();
        }
    }

    public LinkedHashMap<String, OrganisationNode> getDecisions() {
        return decisions;
    }

    public void setDecisions(LinkedHashMap<String, OrganisationNode> decisions) {
        this.decisions = decisions;
    }

    public OrganisationNode removeDecision(String key) {
        return decisions.remove(key);
    }

    public OrganisationNode getNoDecisionNode() {
        return noDecisionNode;
    }

    public void setNoDecisionNode(OrganisationNode noDecisionNode) {
        this.noDecisionNode = noDecisionNode;
    }

    @Override
    public String toString() {
        return (classification == null ? "default" : classification.toString());
    }

    public String printTree(){
        StringBuilder bldr = new StringBuilder();
        if(classification == null)
            return "null";
        bldr.append(classification.toString()+"{\n");
        bldr.append("\tdefault:");
        if(noDecisionNode == null)
            bldr.append("null");
        else
            bldr.append(noDecisionNode.printTree().replaceAll("\n","\n\t"));
        bldr.append('\n');
        for (Map.Entry<String, OrganisationNode> node : decisions.entrySet()) {
            bldr.append('\t');
            bldr.append(node.getKey());
            bldr.append(":");
            bldr.append(node.getValue().printTree().replaceAll("\n", "\n\t"));
            bldr.append('\n');
        }
        bldr.append("}");
        return bldr.toString();
    }
}
