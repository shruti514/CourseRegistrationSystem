package org.courseregistration.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "section_info")
public class Section implements Serializable {

	@Id
	@SequenceGenerator(name = "sequence", sequenceName = "sequence", allocationSize = 1, initialValue = 100000)
	@GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
	@Column(name = "section_id")
	private Long id;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "professor_id", foreignKey = @ForeignKey(name = "FK_section_professor"))
	private Professor professor;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", foreignKey = @ForeignKey(name = "FK_section_course"))
	private Course course;

	@Column(name = "semester", nullable = false)
	private String semester;

	@Column(name = "class_start_time", nullable = false)
	@Temporal(value = TemporalType.TIME)
	private Date classStartTime;

	@Column(name = "class_end_time", nullable = false)
	@Temporal(value = TemporalType.TIME)
	private Date classEndTime;

	@Column(name = "day_of_week", nullable = false)
	private String dayOfWeek;

	@Column(name = "class_start_date", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date startDate;

	@Column(name = "class_end_date", nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date endDate;

	@Column(name = "room_number", nullable = false)
	private String roomNumber;

	@Column(name = "total_capacity", nullable = false)
	private Integer totalCapacity;

	@Column(name = "wait_list_capacity", nullable = false)
	private Integer waitListCapacity;

	@Column(name = "mode_of_instruction", nullable = false)
	private String modeOfInstruction;

	@Column(name = "price", nullable = false)
	private Integer price;

	@JsonIgnore
	@ManyToMany(mappedBy = "sections")
	private List<Student> students;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Date getClassStartTime() {
		return classStartTime;
	}

	public void setClassStartTime(Date classStartTime) {
		this.classStartTime = classStartTime;
	}

	public Date getClassEndTime() {
		return classEndTime;
	}

	public void setClassEndTime(Date classEndTime) {
		this.classEndTime = classEndTime;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(Integer totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public Integer getWaitListCapacity() {
		return waitListCapacity;
	}

	public void setWaitListCapacity(Integer waitListCapacity) {
		this.waitListCapacity = waitListCapacity;
	}

	public String getModeOfInstruction() {
		return modeOfInstruction;
	}

	public void setModeOfInstruction(String modeOfInstruction) {
		this.modeOfInstruction = modeOfInstruction;
	}

	public List<Student> getStudents() {
		return students;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Section))
			return false;

		Section section = (Section) o;

		if (!id.equals(section.id))
			return false;
		if (!professor.equals(section.professor))
			return false;
		if (!course.equals(section.course))
			return false;
		if (!semester.equals(section.semester))
			return false;
		if (!classStartTime.equals(section.classStartTime))
			return false;
		if (!classEndTime.equals(section.classEndTime))
			return false;
		if (!dayOfWeek.equals(section.dayOfWeek))
			return false;
		if (!startDate.equals(section.startDate))
			return false;
		if (!endDate.equals(section.endDate))
			return false;
		if (!roomNumber.equals(section.roomNumber))
			return false;
		if (!totalCapacity.equals(section.totalCapacity))
			return false;
		if (!waitListCapacity.equals(section.waitListCapacity))
			return false;
		return modeOfInstruction.equals(section.modeOfInstruction);

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + professor.hashCode();
		result = 31 * result + course.hashCode();
		result = 31 * result + semester.hashCode();
		result = 31 * result + classStartTime.hashCode();
		result = 31 * result + classEndTime.hashCode();
		result = 31 * result + dayOfWeek.hashCode();
		result = 31 * result + startDate.hashCode();
		result = 31 * result + endDate.hashCode();
		result = 31 * result + roomNumber.hashCode();
		result = 31 * result + totalCapacity.hashCode();
		result = 31 * result + waitListCapacity.hashCode();
		result = 31 * result + modeOfInstruction.hashCode();
		result = 31 * result + price.hashCode();
		return result;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		StringBuilder builder = new StringBuilder();
		builder.append("\n\tSemester: " + semester);
		builder.append(course.toString());
		builder.append("\n\t[ Class timings From " + classStartTime + " To "
				+ classEndTime + "] ");
		builder.append("on " + dayOfWeek + " in a week");
		builder.append("\n\t[ Class Schedule: " + startDate);
		builder.append(" to " + endDate + "]");
		builder.append(" Room: " + roomNumber);
		builder.append("\n\t[ Capacity: total-" + totalCapacity);
		builder.append(", Wait list-" + waitListCapacity + "]");

		builder.append("\n\tMode of Instruction :\t" + modeOfInstruction);

		builder.append("\n\tSection price in USD: \t" + price);

		builder.append(professor.toString());
		builder.append("\n");

		return builder.toString();
	}
}
