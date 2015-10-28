package org.courseregistration.hateoas;

import org.courseregistration.model.Section;
import org.springframework.hateoas.ResourceSupport;

public class SectionResourceWrapper extends ResourceSupport {
    private Section section;

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
