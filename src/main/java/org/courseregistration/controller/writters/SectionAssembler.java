package org.courseregistration.controller.writters;

import org.courseregistration.controller.SectionController;
import org.courseregistration.hateoas.SectionResource;
import org.courseregistration.model.Section;

public class SectionAssembler extends
		ResourceAssembler<Section, SectionResource> {

	public SectionAssembler() {
		super(SectionController.class, SectionResource.class);
	}

	@Override
	public SectionResource toResource(Section entity) {

		SectionResource resource = createResourceWithId(entity.getId(), entity);
		resource.setSection(entity);
		return resource;
	}
}
