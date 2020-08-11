package com.niton.fileorganizer.model.classification.implementation;

import com.niton.fileorganizer.controller.classification.specific.DateClassificationEditorController;
import com.niton.fileorganizer.gui.classification.editors.specific.DateClassificationEditor;
import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.ClassificationType;
import com.niton.fileorganizer.model.classification.types.DateType;
import com.niton.media.filesystem.NFile;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateClassification extends Classification {
    DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                    .withLocale(Locale.getDefault())
                    .withZone(ZoneId.systemDefault());
    private SourceAttribute source = SourceAttribute.CreationTime;
    private String pattern;
    public DateClassification() {
        super(new DateType());
    }

    public void setSource(SourceAttribute source) {
        this.source = source;
    }

    @Override
    public String classify(NFile file) throws IOException {
        return formatter.format(((FileTime) Files.getAttribute(file.getPath(), source.attr, LinkOption.NOFOLLOW_LINKS)).toInstant());
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public enum SourceAttribute {
        LastAccessedTime("lastAccessedTime"),
        LastModifiedTime("lastModifiedTime"),
        CreationTime("creationTime");

        SourceAttribute(String attr) {
            this.attr = attr;
        }

        private final String attr;
    }

    public SourceAttribute getSource() {
        return source;
    }
}
