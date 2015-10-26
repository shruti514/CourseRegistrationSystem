package org.courseregistration.service;

import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.courseregistration.dao.SearchCriteria;
import org.courseregistration.dao.SectionDAO;
import org.courseregistration.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
	@Autowired
	private SectionDAO sectionDAO;

	public List<Section> findAllSections() {
		List<Section> students = sectionDAO.findAll();
		List<Section> toReturn = newArrayList();
		for (Section student : students) {
			toReturn.add(student);
		}
		return toReturn;
	}

	public Section findById(Long id) {
		Section toReturn = sectionDAO.findById(id);
		return toReturn;
	}

	public List<Section> findByName(String name) {
		Map<SearchCriteria, String> criteria = new HashMap<SearchCriteria, String>();
		criteria.put(SearchCriteria.COURSE_NAME_CONTAINS, name);
		List<Section> toReturn = sectionDAO.findCourseByCriteria(criteria);
		return toReturn;
	}

	public boolean addSection(Section section) {
		try {
			sectionDAO.save(section);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteSection(long section_id) {
		sectionDAO.delete(section_id);
	}

	public void update(Section section) {
		Section toReturn = sectionDAO.findById(section.getId());
		toReturn = section;
	}

}
