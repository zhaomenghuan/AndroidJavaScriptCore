package cn.com.agree.abc.sdk.core.javascriptcore;

public abstract class JSObjectWrapper extends JSObject {
    protected JSObjectWrapper(JSObject obj) {
        mJSObject = obj;
        context = obj.getContext();
        valueRef = obj.valueRef();
    }
    private final JSObject mJSObject;

    /**
     * Gets underlying JSObject
     * @return JSObject representing the wrapped object
     * @since 3.0
     */
    public JSObject getJSObject() {
        return mJSObject;
    }
}
