package fr.af.offerpoc.entity;

/**
 * Enum Gender
 */
public enum OfferGenderEnum {
    MALE("M", "Male"), FEMALE("F", "Female");
    private String code;
    private String text;

    private OfferGenderEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }
    // Static method return Gender by code.
    public static OfferGenderEnum getGenderByCode(String code) {
        for (OfferGenderEnum gender : OfferGenderEnum.values()) {
            if (gender.code.equals(code)) {
                return gender;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }



}
