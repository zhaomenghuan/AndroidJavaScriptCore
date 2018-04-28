package cn.com.agree.abc.sdk.core.javascriptcore;

/**
 * A wrapper class for a JavaScript ArrayBuffer
 * See: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer
 * Note, experimental ArrayBuffer.transfer() is not supported by this JavaScriptCore version
 * @since 3.0
 */
public class JSArrayBuffer extends JSObjectWrapper {
    /**
     * Creates a new array buffer of 'length' bytes
     * @param ctx  the JSContext in which to create the ArrayBuffer
     * @param length  the length in bytes of the ArrayBuffer
     * @since 3.0
     */
    public JSArrayBuffer(JSContext ctx, int length) {
        super(new JSFunction(ctx,"_ArrayBuffer",new String[] {"length"},
                "return new ArrayBuffer(length);",
                null, 0).call(null,length).toObject());
    }

    /**
     * Treats an existing JSObject as an ArrayBuffer.  It is up to the user to ensure the
     * underlying JSObject is actually an ArrayBuffer.
     * @param buffer  The ArrayBuffer JSObject to wrap
     * @since 3.0
     */
    public JSArrayBuffer(JSObject buffer) {
        super(buffer);
    }

    /**
     * JavaScript: ArrayBuffer.prototype.byteLength, see:
     * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer/byteLength
     * @return length of ArrayBuffer in bytes
     * @since 3.0
     */
    public int byteLength() {
        return property("byteLength").toNumber().intValue();
    }

    /**
     * JavaScript: ArrayBuffer.isView(), see:
     * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer/isView
     * @param arg the argument to be checked
     * @return true if arg is one of the ArrayBuffer views, such as typed array objects or
     * a DataView; false otherwise
     * @since 3.0
     */
    public static boolean isView(JSValue arg) {
        return arg.getContext().property("ArrayBuffer").toObject().property("isView").toFunction()
                .call(null,arg).toBoolean();
    }

    /**
     * JavaScript: ArrayBuffer.prototype.slice(), see:
     * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer/slice
     * @param begin Zero-based byte index at which to begin slicing
     * @param end Byte index to end slicing
     * @return new ArrayBuffer with sliced contents copied
     * @since 3.0
     */
    public JSArrayBuffer slice(int begin, int end) {
        return new JSArrayBuffer(
                property("slice").toFunction().call(this,begin,end).toObject());
    }
    /**
     * JavaScript: ArrayBuffer.prototype.slice(), see:
     * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer/slice
     * @param begin Zero-based byte index at which to begin slicing
     * @return new ArrayBuffer with sliced contents copied
     * @since 3.0
     */
    public JSArrayBuffer slice(int begin) {
        return new JSArrayBuffer(
                property("slice").toFunction().call(this,begin).toObject());
    }
}
