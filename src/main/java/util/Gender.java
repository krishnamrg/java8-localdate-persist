package util;

/**
 * Created by krishnamg on 21/11/17.
 */
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER"),
    CONFIDENTIAL("CONFIDENTIAL");

    String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
