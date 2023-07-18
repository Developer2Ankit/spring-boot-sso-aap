package com.sso.api;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;

import net.bytebuddy.utility.RandomString;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 byte[] array = new byte[7]; // length is bounded by 7
		    new Random().nextBytes(array);
		    RandomString session = new RandomString();
		    System.out.println(session);
		    String generatedString = new String(array, Charset.forName("UTF-8"));

		    System.out.println(generatedString);
		    Random random = new Random();
		    createRandomInteger(1,10,random);
	}
	 private static void createRandomInteger(int aStart, long aEnd, Random aRandom){
		    if ( aStart > aEnd ) {
		      throw new IllegalArgumentException("Start cannot exceed End.");
		    }
		    //get the range, casting to long to avoid overflow problems
		    long range = aEnd - (long)aStart + 1;
		    // compute a fraction of the range, 0 <= frac < range
		    long fraction = (long)(range * aRandom.nextDouble());
		    long randomNumber =  fraction + (long)aStart; 
System.out.println(randomNumber);
long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

System.out.println(number);
}
}
