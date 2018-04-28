package cn.com.agree.abc.sdk.core.javascriptcore;

/**
 * A convenience class for creating JavaScript values from JSON 
 * @since 3.0
 */
public class JSON extends JSValue {
    private JSON(JSContext ctx, String str) {
        context = ctx;
        valueRef = this.makeFromJSONString(context.ctxRef(), new JSString(str).stringRef());
    }

    /**
     * Gets a JSON string representation of any object
     * @param value The JSValue to convert to JSON
     * @return a JSON string representing 'object'
     * @since 3.0
     */
    public static String stringify(JSValue value) {
        return value.getContext().property("JSON").toObject()
                .property("stringify").toFunction().call(null,value)
                .toString();
    }

    /**
     * Gets a JSON string representation of any object
     * @param ctx  A js context
     * @param object The object to convert to JSON
     * @return a JSON string representing 'object'
     * @since 3.0
     */
    public static String stringify(JSContext ctx, Object object) {
        return ctx.property("JSON").toObject()
                .property("stringify").toFunction().call(null,object)
                .toString();
    }

    /**
     * Creates a new JavaScript value from a JSString JSON string
     * @param ctx  The context in which to create the value
     * @param json  The string containing the JSON
     * @return a JSValue containing the parsed value, or JSValue.isNull() if malformed
     * @since 3.0
     */
    public static JSValue parse(JSContext ctx, String json) {
        return new JSON(ctx,json);
    }
}
