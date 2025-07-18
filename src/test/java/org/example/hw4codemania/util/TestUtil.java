package org.example.hw4codemania.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class TestUtil {
    private static final String[] line1 = {"Enter the Anime","Documentary", "August 5, 2019", "58", "2.5", "English/Japanese"};
    private static final String[] line2 = {"Dark Forces","Thriller","August 21, 2020","81","2.6","Spanish"};
    private static final String[] line3 = {"The App","Science fiction/Drama","December 26, 2019","79","2.6","Italian"};
    private static final String[] line4 = {"The Open House","Horror thriller","January 19, 2018","94","3.2","English"};
    private static final String[] line5 = {"What Happened to Mr. Cha?","Comedy","January 1, 2021","102","4.3","Korean"};
    private static final String[] line6 = {"The Girl on the Train","Thriller","February 26, 2021","120","4.4","Hindi"};
    private static final String[] line7 = {"5 Star Christmas","Comedy","December 7, 2018","95","4.6","Italian"};
    private static final String[] line8 = {"Rattlesnake","Horror","October 25, 2019","85","4.6","English"};
    private static final String[] line9 = {"Sentinelle","Action","March 5, 2021","80","4.7","French"};
    private static final String[] line10 = {"Christmas Crossfire","Thriller","December 4, 2020","106","4.8","German"};

    public static List<String[]> FILE = List.of(
            line1,
            line2,
            line3,
            line4,
            line5,
            line6,
            line7,
            line8,
            line9,
            line10
    );

}
