package com.niton.fileorganizer.model.classification.implementation;

import com.niton.fileorganizer.model.classification.Classification;
import com.niton.fileorganizer.model.classification.types.DateType;
import com.niton.media.filesystem.NFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.IntFunction;

public class DateClassification extends Classification<DateType> {
    private transient DateTimeFormatter formatter;
    private SourceAttribute source = SourceAttribute.CreationTime;
    private String pattern;
    private boolean
            ignoreMonth = false,
            ignoreDay = false,
            ignoreYear = false;

    public DateClassification() {
        super(new DateType());
        setPattern("dd-MM-uuuu");
    }

    public void setSource(SourceAttribute source) {
        this.source = source;
    }

    @Override
    public void computePossibilities(NFile file) throws IOException {
        if(formatter == null){
            formatter = DateTimeFormatter.ofPattern(pattern)
                    .withLocale(Locale.getDefault())
                    .withZone(ZoneId.systemDefault());
        }
        if(source != SourceAttribute.PATH) {
            addPossibility(

                    formatter.format(((FileTime) Files.getAttribute(file.getPath(), source.attr, LinkOption.NOFOLLOW_LINKS)).toInstant()),
                    1);
            return;
        }
        List<PathNumber> generalNumbers = new ArrayList<>();
        String[] folders = file.getAbsolutePath().split(String.format("\\%s", System.getProperty("file.separator")));
        for (String folder : folders) {
            try {
                int val = Integer.parseInt(folder);
                PathNumber number = new PathNumber(val);
                generalNumbers.add(number);
            } catch (NumberFormatException ignored) {
            }
            String[] parts = folder.split("-");
            if (parts.length == 1)
                parts = folder.split("\\.");
            if (parts.length == 1)
                continue;
            for (String part : parts) {
                try {
                    int val = Integer.parseInt(part);
                    PathNumber number = new PathNumber(val);
                    generalNumbers.add(number);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        for (PathNumber day : generalNumbers) {
            if(day.probabilityDay == 0)
                continue;
            for (PathNumber month : generalNumbers) {
                if(month.probabilityMonth == 0)
                    continue;
                for (PathNumber year : generalNumbers) {
                    int fullYear = year.value;
                    if (fullYear<100)
                        if(fullYear <= LocalDate.now().getYear()%100)
                            fullYear+=2000;
                        else
                            fullYear += 1900;
                    if(year.probabilityYear == 0)
                        continue;
                    addPossibility(formatter.format(LocalDate.of(fullYear, month.value, day.value)),
                            (ignoreDay?1:day.probabilityDay)*
                                    (ignoreMonth?1:month.probabilityMonth)*
                                    (ignoreYear?1:year.probabilityYear));
                }

            }

        }

    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;

        formatter = DateTimeFormatter.ofPattern(pattern)
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
    }

    public boolean isIgnoreYear() {
        return ignoreYear;
    }

    public void setIgnoreYear(boolean ignoreYear) {
        this.ignoreYear = ignoreYear;
    }

    public boolean isIgnoreDay() {
        return ignoreDay;
    }

    public void setIgnoreDay(boolean ignoreDay) {
        this.ignoreDay = ignoreDay;
    }

    public boolean isIgnoreMonth() {
        return ignoreMonth;
    }

    public void setIgnoreMonth(boolean ignoreMonth) {
        this.ignoreMonth = ignoreMonth;
    }

    public enum SourceAttribute {
        LastAccessedTime("lastAccessedTime"),
        LastModifiedTime("lastModifiedTime"),
        CreationTime("creationTime"),
        PATH(null);

        SourceAttribute(String attr) {
            this.attr = attr;
        }

        private final String attr;
    }

    private static class PathNumber {
        public int value;
        public double probabilityDay,probabilityMonth,probabilityYear;

        public PathNumber(int val) {
            this.value = val;
            if(val > 1500){
                probabilityDay = 0;
                probabilityMonth = 0;
                probabilityYear = 0.108;

                if(val < 1800){
                    probabilityYear /= 2.5;
                }
                if(val >= 1900){
                    probabilityYear *= 2;
                }
                if(val >= 2000){
                    probabilityYear *= 1.3;
                }
                if(val+15 >= LocalDate.now().getYear()){
                    probabilityYear *= 2.8;
                }
                if(val > LocalDate.now().getYear())
                    probabilityYear /= 1.75;
                if(val-15 > LocalDate.now().getYear())
                    probabilityYear /= 3;
            }else if(val > 0 && val <= 31){
                if(val > LocalDate.now().getYear()%100)
                    probabilityYear = 0.02;
                else
                    probabilityYear = 0.15;
                if(val <= 12){
                    probabilityMonth = 0.7;
                    probabilityDay = 0.4;
                }else{
                    probabilityMonth = 0;
                    probabilityDay = 0.6;
                }

            }else if (val > 60 && val < 100){
                probabilityYear = 0.75;
                probabilityDay = 0;
                probabilityMonth = 0;
            }
        }
    }

    public SourceAttribute getSource() {
        return source;
    }

    static <T> List<T[]> combinationsWithRepetition(Collection<T> set, int k, IntFunction<T[]> arrayFunction) {
        List<T[]> lst = new ArrayList<>();
        printAllKLengthRec(set,k,lst,arrayFunction.apply(k));
        return lst;
    }

    static <T>void printAllKLengthRec(Collection<T> set, int k,List<T[]> result,T[] array){
        for (T t : set){
            array[array.length-k] = t;
            if(k == 1)
                result.add(Arrays.copyOf(array,array.length));
            else
                printAllKLengthRec(set, k - 1, result, array);
        }
    }

    public static void main(String[] args) throws IOException {
        DateClassification klassisch = new DateClassification();
        klassisch.setSource(SourceAttribute.PATH);
        klassisch.setPattern("uuuu");
        klassisch.setIgnoreDay(true);
        klassisch.setIgnoreMonth(true);
        klassisch.computePossibilities(new NFile("D:\\Users\\Nils\\Desktop\\Test\\chaos_fs\\2003\\23.10\\Bilder"));
        System.out.println(klassisch.getPossibilitieMap());
    }
}
