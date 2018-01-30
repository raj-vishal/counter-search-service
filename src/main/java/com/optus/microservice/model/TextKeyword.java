package com.optus.microservice.model;

public class TextKeyword {

	 String keyWord;
	 long counter;

	 public TextKeyword(String keyword, int count) {
		 keyWord = keyword;
		 counter = count;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

}