package com.yanxing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        String test="【床裙三件套】优诗莱【床裙单件+枕套一件】斜纹磨毛床裙床罩";
        test.replaceAll("【[\\s\\S]*】","");
    }
}