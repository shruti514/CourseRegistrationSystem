package org.courseregistration.rest.writters;

import org.courseregistration.rest.SectionResource;
import org.courseregistration.hateoas.SectionResourceWrapper;
import org.courseregistration.model.Section;

public class SectionAssembler extends
		ResourceAssembler<Section, SectionResourceWrapper> {

	public SectionAssembler() {
		super(SectionResource.class, SectionResourceWrapper.class);
	}

	@Override
	public SectionResourceWrapper toResource(Section entity) {

		SectionResourceWrapper resource = createResourceWithId(entity.getId(), entity);
		resource.setSection(entity);
		return resource;
	}
}
