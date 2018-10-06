package com.example.shibu.android_ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class TestData implements Parcelable {

    int index = 0;

    String name = "wps";

    private TestData(Parcel in) {
        index = in.readInt();
        name = in.readString();
    }

    public static final ClassLoaderCreator<TestData> CREATOR = new ClassLoaderCreator<TestData>() {
        @Override
        public TestData createFromParcel(Parcel source, ClassLoader loader) {
            return new TestData(source);
        }

        @Override
        public TestData createFromParcel(Parcel in) {
            return new TestData(in);
        }


        @Override
        public TestData[] newArray(int size) {
            return new TestData[size];
        }
    };


    public TestData(int i, String name) {
        this.index = i;
        this.name = name;
    }

    /***
     *
     * @return 一般对象返回0，如果包含文件描述符需要返回{@link #CONTENTS_FILE_DESCRIPTOR}.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return getClass().getName() + "index: "+index +";name:"+name;
    }
}
