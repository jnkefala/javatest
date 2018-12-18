package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.util.AppUtil;

public class Tests {
	
	@Test
	public void passNull(){
		assertFalse(AppUtil.isPassValid(null));
	}
	
	@Test
	public void passLessThanEight(){
		assertFalse(AppUtil.isPassValid("ab"));
	}
	
	@Test
	public void passMoreThanSixteen(){
		assertFalse(AppUtil.isPassValid("abcdefghijklmnopqrs"));
	}
	
	@Test
	public void passWihtoutLetters(){
		assertFalse(AppUtil.isPassValid("0123456789"));
	}
	
	@Test
	public void passWihtoutNumber(){
		assertFalse(AppUtil.isPassValid("abcdefghi"));
	}
	
	@Test
	public void passWihtoutSpecialCharacter(){
		assertFalse(AppUtil.isPassValid("abcdef4ghi"));
	}
	
	@Test
	public void passValid(){
		assertTrue(AppUtil.isPassValid("abc_dEf4ghi"));
	}
	
}
