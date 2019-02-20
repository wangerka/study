package com.example.serial;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
	int age;
	String name;
	
	
	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeInt(age);
		arg0.writeString(name);
	}
	
	public final static Creator<Person> CREATOR = new Creator<Person>() {

		@Override
		public Person createFromParcel(Parcel arg0) {
			// TODO Auto-generated method stub
			int age = arg0.readInt();
			String name = arg0.readString();
			return new Person(age,name);
		}

		@Override
		public Person[] newArray(int arg0) {
			// TODO Auto-generated method stub
			return new Person[arg0];
		}
	};

}
