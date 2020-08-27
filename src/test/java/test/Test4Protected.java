package test;

import com.shenlanbao.item15.TestForAccessor;

public class Test4Protected extends TestForAccessor{

    public void test() {
        protectedInteger = 1;
    }
    public static void main(String[] args) {
        TestForAccessor testForAccessor = new TestForAccessor();
        Integer publicInteger = testForAccessor.publicInteger;
    }
}
