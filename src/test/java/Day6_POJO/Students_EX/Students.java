
package Day6_POJO.Students_EX;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Students {

    @SerializedName("students")
    @Expose
    private List<Student> students = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Students() {
    }

    /**
     * 
     * @param students
     */
    public Students(List<Student> students) {
        super();
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
