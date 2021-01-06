
package alaa.meaad.mobiletask_dms.repositry.model.home;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Home {

    @SerializedName("data")
    @Expose
    private List<DataHome> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public List<DataHome> getData() {
        return data;
    }

    public void setData(List<DataHome> data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
