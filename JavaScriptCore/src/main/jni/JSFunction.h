#ifndef ANDROIDJSCORE_JSFUNCTION_H
#define ANDROIDJSCORE_JSFUNCTION_H

#include "Instance.h"

class JSFunction : public Instance {
    public:
        JSFunction(JNIEnv *env, jobject thiz, JSContextRef ctx, JSStringRef name = NULL);
        virtual ~JSFunction();

    private:
        static JSValueRef StaticFunctionCallback(JSContextRef ctx, JSObjectRef function,
                JSObjectRef thisObject,size_t argumentCount, const JSValueRef arguments[],
                JSValueRef* exception);
        static JSObjectRef StaticConstructorCallback(JSContextRef ctx,
                JSObjectRef constructor,size_t argumentCount,const JSValueRef arguments[],
                JSValueRef* exception);
        static bool StaticHasInstanceCallback(JSContextRef ctx, JSObjectRef constructor,
                JSValueRef possibleInstance, JSValueRef* exception);
        static JSClassDefinition JSFunctionClassDefinition();

        JSObjectRef ConstructorCallback(JSContextRef ctx, JSObjectRef constructor,
                size_t argumentCount, const JSValueRef arguments[], JSValueRef* exception);
        JSValueRef FunctionCallback(JSContextRef ctx, JSObjectRef function,
                JSObjectRef thisObject, size_t argumentCount,const JSValueRef arguments[],
                JSValueRef* exception);
        bool HasInstanceCallback(JSContextRef ctx, JSObjectRef constructor,
                JSValueRef possibleInstance, JSValueRef* exception);
};

#endif //ANDROIDJSCORE_JSFUNCTION_H
