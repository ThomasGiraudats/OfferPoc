package fr.af.offerpoc.entity;

/**
 * Enum Gender
 *
 * @Author TGI
 * @Date 24/03/2022
 */
public enum OfferGenderEnum {
    M("M", "Male"), F("F", "Female");
    private String code;
    private String text;

    OfferGenderEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    /**
     * return an enum by her code in parameter
     *
     * @param code
     * @return OfferGenderEnum
     */
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
