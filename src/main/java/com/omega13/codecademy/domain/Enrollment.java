package com.omega13.codecademy.domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class Enrollment {
    private int id;
    private java.sql.Date registrationDate;
    private int courseMemberId;
    private int certificateId;
    private int courseId;

    public Enrollment(int id, Date registrationDate, int courseMemberId, int certificateId, int courseId) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.courseMemberId = courseMemberId;
        this.certificateId = certificateId;
        this.courseId = courseId;
    }

    public int getCourseMemberId() {
        return this.courseMemberId;
    }

    public int getCourseId() {
        return this.courseId;
    }


    public int getId() {
        return this.id;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public int getCertificateId() {
        return this.certificateId;
    }
}
