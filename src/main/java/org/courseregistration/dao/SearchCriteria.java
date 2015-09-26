package org.courseregistration.dao;



public enum SearchCriteria {

    COURSE_NAME("like"),
    DEPARTMENT("equal"),
    SEMESTER("equal"),
    DAY_OF_WEEK("equal"),
    PROFESSOR_NAME_BEGINS_WITH("like"),
    PROFESSOR_NAME_ENDS_WITH("like"),
    PROFESSOR_NAME_EXACTLY("equal"),
    COURSE_NUMBER_EXACTLY("equal"),
    COURSE_NUMBER_LESS_THAN_OR_EQUAL("equal"),
    COURSE_NUMBER_GREATER_THAN_OR_EQUAL("equal"),
    COURSE_NUMBER_CONTAINS("like");



    private SearchCriteria(String operator){
        this.operator = operator;
    }

    private String operator;

}
