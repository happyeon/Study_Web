package com.eljstl.score;

public class Score {
	
	private String name;
	private int kor_score;
	private int eng_score;
	private int math_score;
	private int sum;
	private double avg;
	private String grade;
	
	public Score() {
		
	}
	
	public Score(String name, int kor_score, int eng_score, int math_score) {
		this.name = name;
		this.kor_score = kor_score;
		this.eng_score = eng_score;
		this.math_score = math_score;
		setSum();
		setAvg();
		setGrade();
	}
	
	
	public Score(String name, int kor_score, int eng_score, int math_score, int sum, double avg, String grade) {
		this.name = name;
		this.kor_score = kor_score;
		this.eng_score = eng_score;
		this.math_score = math_score;
		this.sum = sum;
		this.avg = avg;
		this.grade = grade;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKor_score() {
		return kor_score;
	}


	public void setKor_score(int kor_score) {
		this.kor_score = kor_score;
	}


	public int getEng_score() {
		return eng_score;
	}


	public void setEng_score(int eng_score) {
		this.eng_score = eng_score;
	}


	public int getMath_score() {
		return math_score;
	}


	public void setMath_score(int math_score) {
		this.math_score = math_score;
	}


	public int getSum() {
		return sum;
	}


	public void setSum() {
		this.sum = kor_score + eng_score + math_score;
	}


	public double getAvg() {
		return avg;
	}


	public void setAvg() {
		this.avg = (double) sum / 3;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade() {
		
		switch((int)avg/10) {
		case 10:
		case 9:
			this.grade = "A";
			break;
		case 8:
			this.grade = "B";
			break;
		case 7:
			this.grade = "C";
			break;
		case 6:
			this.grade = "D";
			break;
		default:
			this.grade = "F";
			break;
		}
		
	}
	
	
	
	
	

}
