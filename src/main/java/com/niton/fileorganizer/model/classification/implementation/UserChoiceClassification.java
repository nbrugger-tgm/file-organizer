package com.niton.fileorganizer.model.classification.implementation;

import com.google.common.collect.ArrayListMultimap;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.types.UserChoiceType;
import com.niton.media.filesystem.NFile;
import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserChoiceClassification extends Classification<UserChoiceType> {
    private List<String> options = new ArrayList<>();
    private static final transient StringMetric comparator = StringMetrics.jaroWinkler();
    public UserChoiceClassification() {
        super(new UserChoiceType());
    }

    @Override
    public void computePossibilities(NFile file) throws IOException {
        String name = file.getAbsolutePath();
        for (String option : options) {
            double score = 0;
            for (String s : name.split(String.format("\\%s", System.getProperty("file.separator")))) {
                double a = comparator.compare(s, option);
                if(a > score)
                    score = a;
            }
           addPossibility(option,score);
        }
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
