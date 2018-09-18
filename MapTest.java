import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size
    
    //Nik - add, remove, value, size
    //Hudson - hasKey, constructor, removeAny
    
    //HUDSON'S TEST CASES FROM HOMEWORK 9
     /*
     * standard test case for Map<String,String> constructor
     */
    @Test
    public final void constructorTest1() {
        Map<String, String> m = this.constructorTest();
        Map<String, String> mExpected = this.constructorRef();
        assertEquals(mExpected, m);
    }

    /*
     * standard test case for Map<String,String> add method
     */
    @Test
    public final void addTest1() {
        Map<String, String> m = this.createFromArgsTest("key1", "value1");
        Map<String, String> mExpected = this.createFromArgsRef("key1", "value1",
                "key2", "value2");
        m.add("key2", "value2");
        assertEquals(mExpected, m);
    }

    /*
     * standard test case for Map<String,String> remove method
     */

    @Test
    public final void removeTest1() {
        Map<String, String> mExpected = this.createFromArgsTest("key1",
                "value1");
        Map<String, String> m = this.createFromArgsRef("key1", "value1", "key2",
                "value2");
        m.remove("key2");
        assertEquals(mExpected, m);
    }

    /*
     * standard test case for Map<String,String> remove method
     */
    @Test
    public final void removeTest2() {
        Map<String, String> m = this.createFromArgsTest("key1", "value1");
        Map<String, String> mExpected = this.createFromArgsRef();
        m.remove("key1");
        assertEquals(mExpected, m);

    }

    /*
     * standard test case for Map<String,String> removeAny method
     */
    public final void removeAnyTest1() {
        Map<String, String> m = this.createFromArgsRef("key1", "value1", "key2",
                "value2");
        Map<String, String> mExpected = this.createFromArgsTest("key1",
                "value1", "key2", "value2");
        Map.Pair<String, String> p = m.removeAny();
        assertEquals(true, mExpected.hasKey(p.key()));
        mExpected.remove(p.key());
        assertEquals(mExpected, m);
    }

    /*
     * standard test case for Map<String,String> value method
     */
    @Test
    public final void valueTest1() {
        Map<String, String> m = this.createFromArgsTest("key1", "value1");
        Map<String, String> mExpected = this.createFromArgsRef("key1",
                "value1");
        String s = m.value("key1");
        String sExpected = "value1";
        assertEquals(mExpected, m);
        assertEquals(sExpected, s);
    }

    /*
     * standard test case for Map<String,String> hasKey method that returns true
     */
    @Test
    public final void hasKeyTest1() {
        Map<String, String> m = this.createFromArgsTest("key1", "value1");
        Map<String, String> mExpected = this.createFromArgsRef("key1",
                "value1");
        boolean b = m.hasKey("key1");
        boolean bExpected = true;
        assertEquals(mExpected, m);
        assertEquals(bExpected, b);

    }

    /*
     * standard test case for Map<String,String> hasKey method that returns
     * false
     */
    @Test
    public final void hasKeyTest2() {
        Map<String, String> m = this.createFromArgsTest("key1", "value1");
        Map<String, String> mExpected = this.createFromArgsRef("key1",
                "value1");
        boolean b = m.hasKey("key2");
        boolean bExpected = false;
        assertEquals(mExpected, m);
        assertEquals(bExpected, b);
    }

    /*
     *
     * standard test case for Map<String,String> size method
     *
     */

    @Test
    public final void sizeTest1() {
        Map<String, String> m = this.createFromArgsTest("key1", "value1");
        Map<String, String> mExpected = this.createFromArgsRef("key1",
                "value1");
        int i = m.size();
        int iExpected = 1;
        assertEquals(mExpected, m);
        assertEquals(iExpected, i);
    }

    /*
     * standard test case for Map<String,String> size method with empty map
     */
    @Test
    public final void sizeTest2() {
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        int i = m.size();
        int iExpected = 0;
        assertEquals(mExpected, m);
        assertEquals(iExpected, i);
    }
}

}
