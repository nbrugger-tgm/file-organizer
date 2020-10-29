package com.niton.fileorganizer.model.classification.implementation;

import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.types.UserInputType;
import com.niton.media.filesystem.NFile;

import java.io.IOException;

public class UserInputClassification extends Classification<UserInputType> {

    public UserInputClassification() {
        super(new UserInputType());
    }

    @Override
    public void computePossibilities(NFile file)  {}
}
