package com.example.sistematec;

public class Data {
    //////////////////////////////////////////


    //////////////////////////////////////////
    //
    private static int REQ_PDF = 21;



    //////////////////////////////////////////
    private static int loggedUser;
    private static String loginId;
    private static String loginPass;

    //////////////////////////////////////////
    //User Data
    //Student
    private static String studentId;
    private static String studentName;
    private static String studentCareer;
    private static String studentSemester;
    private static String studentDepId;
    private static String studentSolId;
    private static String studentSolPhaseId;
    private static String studentSolPhaseDescription;
    private static String studentPhotoURL;


    //Coord/Academy

    private static String coordAcId;
    private static String coordAcName;
    private static String coordAcDepId;
    private static String coordAcDepName;
    private static String coorAcPhotoURL;



    public static int getReqPdf() {
        return REQ_PDF;
    }

    public static String getStudentId() {
        return studentId;
    }

    public static void setStudentId(String studentId) {
        Data.studentId = studentId;
    }

    public static String getStudentName() {
        return studentName;
    }

    public static void setStudentName(String studentName) {
        Data.studentName = studentName;
    }

    public static String getStudentCareer() {
        return studentCareer;
    }

    public static void setStudentCareer(String studentCareer) {
        Data.studentCareer = studentCareer;
    }

    public static String getStudentSemester() {
        return studentSemester;
    }

    public static void setStudentSemester(String studentSemester) {
        Data.studentSemester = studentSemester;
    }

    public static String getStudentDepId() {
        return studentDepId;
    }

    public static void setStudentDepId(String studentDepId) {
        Data.studentDepId = studentDepId;
    }

    public static String getStudentSolId() {
        return studentSolId;
    }

    public static void setStudentSolId(String studentSolId) {
        Data.studentSolId = studentSolId;
    }

    public static String getStudentSolPhaseId() {
        return studentSolPhaseId;
    }

    public static void setStudentSolPhaseId(String studentSolPhaseId) {
        Data.studentSolPhaseId = studentSolPhaseId;
    }

    public static String getStudentSolPhaseDescription() {
        return studentSolPhaseDescription;
    }

    public static void setStudentSolPhaseDescription(String studentSolPhaseDescription) {
        Data.studentSolPhaseDescription = studentSolPhaseDescription;
    }

    public static String getStudentPhotoURL() {
        return studentPhotoURL;
    }

    public static void setStudentPhotoURL(String studentPhotoURL) {
        Data.studentPhotoURL = studentPhotoURL;
    }

    public static String getCoordAcId() {
        return coordAcId;
    }



    public static void setCoordAcId(String coordAcId) {
        Data.coordAcId = coordAcId;
    }

    public static String getCoordAcName() {
        return coordAcName;
    }

    public static void setCoordAcName(String coordAcName) {
        Data.coordAcName = coordAcName;
    }

    public static String getCoordAcDepId() {
        return coordAcDepId;
    }

    public static void setCoordAcDepId(String coordAcDepId) {
        Data.coordAcDepId = coordAcDepId;
    }

    public static String getCoordAcDepName() {
        return coordAcDepName;
    }

    public static void setCoordAcDepName(String coordAcDepName) {
        Data.coordAcDepName = coordAcDepName;
    }

    public static String getCoorAcPhotoURL() {
        return coorAcPhotoURL;
    }

    public static void setCoorAcPhotoURL(String coorAcPhotoURL) {
        Data.coorAcPhotoURL = coorAcPhotoURL;
    }

    public static int getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(int loggedUser) {
        Data.loggedUser = loggedUser;
    }

    public static String getLoginId() {
        return loginId;
    }

    public static void setLoginId(String loginId) {
        Data.loginId = loginId;
    }

    public static String getLoginPass() {
        return loginPass;
    }

    public static void setLoginPass(String loginPass) {
        Data.loginPass = loginPass;
    }

    public static void resetLogindata() {
        Data.loggedUser = 0;
        Data.loginId = null;
        Data.loginPass = null;
    }
}


