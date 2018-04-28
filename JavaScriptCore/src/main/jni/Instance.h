#ifndef ANDROIDJSCORE_INSTANCE_H
#define ANDROIDJSCORE_INSTANCE_H

#include "JSJNI.h"
#include <map>
#include <mutex>

class Instance {
public:
    Instance(JNIEnv *env, jobject thiz, JSContextRef ctx,
        JSClassDefinition def = kJSClassDefinitionEmpty, JSStringRef name = NULL);
    virtual ~Instance();
    virtual long getObjRef() { return (long) objRef; }
    static Instance* getInstance(JSObjectRef objref);

protected:
    JavaVM *jvm;
    jobject thiz;

private:
    JSObjectRef objRef;
    JSClassRef classRef;
    JSClassDefinition definition;

    static std::map<JSObjectRef,Instance *> objMap;
    static std::mutex mutex;

    static void StaticFinalizeCallback(JSObjectRef object);
};

#endif //ANDROIDJSCORE_INSTANCE_H
