package Day6_POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionPost {

    @SerializedName("region_id")
    @Expose
    private Integer regionId;
    @SerializedName("region_name")
    @Expose
    private String regionName;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegionPost() {
    }

    /**
     *
     * @param regionId
     * @param regionName
     */
    public RegionPost(Integer regionId, String regionName) {
        super();
        this.regionId = regionId;
        this.regionName = regionName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

}