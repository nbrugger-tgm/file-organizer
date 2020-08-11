package com.niton.fileorganizer.model.classification.implementation;

import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.types.UserInputType;
import com.niton.media.filesystem.NFile;

import java.io.IOException;

public class UserInputClassification extends Classification {

    public UserInputClassification() {
        super(new UserInputType());
    }

    @Override
    public String classify(NFile file)  {
        return "";
    }
}
