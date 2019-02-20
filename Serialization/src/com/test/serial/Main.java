package com.test.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Person p = new Person();
		p.setAge(188);
		p.setName("gejun");
		
		try {
			ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("F:\\p.txt")));
			oo.writeObject(p);
			oo.close();
			
			ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("F:\\p.txt")));
			Person pp = (Person)oi.readObject();
			System.out.println("age = "+pp.getAge()+", name = "+pp.getName());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
