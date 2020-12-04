
package Day6_POJO.Students_EX;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("companyId")
    @Expose
    private Integer companyId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("address")
    @Expose
    private Address address;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Company() {
    }

    /**
     * 
     * @param companyId
     * @param address
     * @param companyName
     * @param title
     * @param startDate
     */
    public Company(Integer companyId, String companyName, String title, String startDate, Address address) {
        super();
        this.companyId = companyId;
        this.companyName = companyName;
        this.title = title;
        this.startDate = startDate;
        this.address = address;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
