package com.liukhtenko.informatiohandling.reader;

import com.liukhtenko.informationhandling.exeption.CustomException;
import com.liukhtenko.informationhandling.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataReaderTest {
    private DataReader dataReader;

    @BeforeClass
    public void setup() {
        dataReader = new DataReader();
    }

    @AfterClass
    public void tearDown() {
        dataReader = null;
    }

    @Test
    public void readTestTrue() throws CustomException {
        String expected = "    It has survived - not only (five) centuries, but also the leap into electronictype setting, " +
                "remaining essentially unchanged. It was popularised in the “Динамо” (Рига)with the release of Letraset sheets " +
                "containing Lorem Ipsum passages, and morerecently with desktop publishing software like Aldus PageMaker Faclon9 " +
                "includingversions of Lorem Ipsum!    It is a long a!=b established fact that a reader will be distracted by the " +
                "readablecontent of a page when looking at its layout. The point of using Ipsum is that it has amore-or-less normal " +
                "distribution ob.toString(a?b:c), as opposed to using (Content here),content here's, making it look like readable English?    " +
                "It is a established fact that a reader will be of a page when looking at its layout...    Bye бандерлоги.";
        String actual = dataReader.read();
        Assert.assertEquals(expected, actual);
    }
}
