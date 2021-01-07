
package alaa.meaad.mobiletask_dms.repositry.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("status")
    @Expose
    private Boolean status;

//    @SerializedName("counts")
//    @Expose
//    private Counts counts;
//
//
//    public Counts getCounts() {
//        return counts;
//    }
//
//    public void setCounts(Counts counts) {
//        this.counts = counts;
//    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
