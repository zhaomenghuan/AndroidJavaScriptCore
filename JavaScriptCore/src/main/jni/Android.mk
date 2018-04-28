LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := javascriptcore
LOCAL_SRC_FILES := libs/$(TARGET_ARCH_ABI)/libjavascriptcoregtk-4.0.so
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := android-js-core
LOCAL_SRC_FILES := JSContext.cpp \
                   JSObject.cpp \
                   JSString.cpp \
                   JSValue.cpp \
                   Instance.cpp \
                   JSFunction.cpp

LOCAL_SHARED_LIBRARIES := javascriptcore

LOCAL_CPPFLAGS  := -std=c++11 -fexceptions -I$(LOCAL_PATH)/include
LOCAL_LDFLAGS := -llog

include $(BUILD_SHARED_LIBRARY)