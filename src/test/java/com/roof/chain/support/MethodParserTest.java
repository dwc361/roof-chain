package com.roof.chain.support;

import com.roof.chain.api.MethodParamDescriptor;
import org.junit.Test;

public class MethodParserTest {
    @Test
    public void testObjectMethod() {
        MethodParser parser = new MethodParser(TestObject.class, "testMethod");
        MethodParamDescriptor[] methodParameters = parser.getMethodParameters();
        for (MethodParamDescriptor methodParameter : methodParameters) {
            System.out.println(methodParameter);
        }
    }
}
