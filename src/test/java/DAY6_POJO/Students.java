package DAY6_POJO;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Students{

	@SerializedName("students")
	private List<StudentsItem> students;

	public void setStudents(List<StudentsItem> students){
		this.students = students;
	}

	public List<StudentsItem> getStudents(){
		return students;
	}

	@Override
 	public String toString(){
		return 
			"Students{" + 
			"students = '" + students + '\'' + 
			"}";
		}
}