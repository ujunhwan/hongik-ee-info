package info.hongik.ee.domain.user;

import lombok.Getter;

@Getter
public class GraduationDto {

    public String dragonBall1;
    public String dragonBall2;
    public String dragonBall3;
    public String dragonBall4;
    public String dragonBall5;
    public String dragonBall6;
    public String dragonBall7;
    public String dragonBall8;

    // 교양필수
    public String requiredElective;

    // 기본/소양/교양 계
    public String reqElectiveSum;
    // 교양선택
    public String selective;
    // 계
    public String electiveTotalSum;

    // 졸업인정학점
    public String graduationCreditElective;

    public String science;
    public String math;
    public String computer;
    public String MSCtotal;
    public String MSCtotal2;

    public String major;
    // 전공기초
    public String basicMajor;
    // 일반선택
    public String generalElective;

    // 취득학점
    public String totalCredit;

    // 졸업인정학점
    public String graduationTotalCredit;

    // 평점
    public String GPA;

    // 전공평점
    public String majorGPA;

    public GraduationDto(String[] array) {
        this.dragonBall1 = array[0];
        this.dragonBall2 = array[1];
        this.dragonBall3 = array[2];
        this.dragonBall4 = array[3];
        this.dragonBall5 = array[4];
        this.dragonBall6 = array[5];
        this.dragonBall7 = array[6];
        this.dragonBall8 = array[7];

        this.requiredElective = array[8];
        this.reqElectiveSum = array[9];
        this.selective = array[10];
        this.electiveTotalSum = array[11];

        this.graduationCreditElective = array[12];
        this.science = array[13];
        this.math = array[14];
        this.computer = array[15];

        this.MSCtotal = array[16];
        this.MSCtotal2 = array[17];

        this.major = array[18];
        this.basicMajor = array[19];

        this.generalElective = array[20];
        this.totalCredit = array[21];
        this.graduationTotalCredit = array[22];
        this.GPA = array[23];
        this.majorGPA = array[24];
    }
}
