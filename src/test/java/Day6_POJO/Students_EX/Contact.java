
package Day6_POJO.Students_EX;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("contactId")
    @Expose
    private Integer contactId;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("premanentAddress")
    @Expose
    private String premanentAddress;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Contact() {
    }

    /**
     * 
     * @param emailAddress
     * @param contactId
     * @param phone
     * @param premanentAddress
     */
    public Contact(Integer contactId, String phone, String emailAddress, String premanentAddress) {
        super();
        this.contactId = contactId;
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.premanentAddress = premanentAddress;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPremanentAddress() {
        return premanentAddress;
    }

    public void setPremanentAddress(String premanentAddress) {
        this.premanentAddress = premanentAddress;
    }

}
