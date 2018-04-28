package cn.com.agree.abc.sdk.core.javascriptcore;

/**
 * A JSContextGroup associates JavaScript contexts with one another. Contexts
 * in the same group may share and exchange JavaScript objects. Sharing and/or
 * exchanging JavaScript objects between contexts in different groups will produce
 * undefined behavior. When objects from the same context group are used in multiple
 * threads, explicit synchronization is required.
 *
 */
public class JSContextGroup {
    private Long group;

    /**
     * Creates a new context group
     * @since 1.0
     */
    public JSContextGroup() {
        group = create();
    }
    /**
     * Wraps an existing context group
     * @param groupRef  the JavaScriptCore context group reference
     * @since 1.0
     */
    public JSContextGroup(Long groupRef)
    {
        group = groupRef;
    }

    @Override
    protected void finalize() throws Throwable {
        if (group!=0) release(group);
        super.finalize();
    }

    /**
     * Gets the JavaScriptCore context group reference
     * @since 1.0
     * @return  the JavaScriptCore context group reference
     */
    public Long groupRef() {
        return group;
    }

    /**
     * Checks if two JSContextGroups refer to the same JS context group
     * @param other the other object to compare
     * @return true if refer to same context group, false otherwise
     * @since 2.2
     */
    @Override
    public boolean equals(Object other) {
        return (other !=null) &&
                (this == other) ||
                (other instanceof JSContextGroup) &&
                !(groupRef() == null || groupRef() == 0) &&
                groupRef().equals(((JSContextGroup)other).groupRef());
    }

    protected native long create();
    protected native long retain(long group);
    protected native void release(long group);
}
