#include "Instance.h"

Instance::Instance(JNIEnv *env, jobject thiz, JSContextRef ctx,
        JSClassDefinition def, JSStringRef name)
{
    env->GetJavaVM(&jvm);
    definition = def;
    definition.finalize = StaticFinalizeCallback;
    classRef = JSClassCreate(&definition);
    objRef = JSObjectMake(ctx, classRef, name);
    JSValueProtect(ctx, objRef);
    this->thiz = env->NewWeakGlobalRef(thiz);

    mutex.lock();
    objMap[objRef] = this;
    mutex.unlock();
}

Instance::~Instance()
{
    JSClassRelease(classRef);
    JNIEnv *env;
    int getEnvStat = jvm->GetEnv((void**)&env, JNI_VERSION_1_6);
    if (getEnvStat == JNI_EDETACHED) {
        jvm->AttachCurrentThread(&env, NULL);
    }
    env->DeleteWeakGlobalRef(thiz);

    mutex.lock();
    objMap.erase(objRef);
    mutex.unlock();

    if (getEnvStat == JNI_EDETACHED) {
        jvm->DetachCurrentThread();
    }
}

Instance* Instance::getInstance(JSObjectRef objref)
{
    Instance *inst = NULL;
    mutex.lock();
    inst = objMap[objref];
    mutex.unlock();
    return inst;
}

std::map<JSObjectRef,Instance *> Instance::objMap = std::map<JSObjectRef,Instance *>();
std::mutex Instance::mutex;

void Instance::StaticFinalizeCallback(JSObjectRef object)
{
    Instance *thiz = getInstance(object);

    if (thiz) {
        delete thiz;
    }
}
