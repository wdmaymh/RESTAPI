package ac.korea.isdevelop.restapi.billMaster;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class BillMasterResource extends EntityModel<BillMaster> {

    public BillMasterResource(BillMaster billMaster) {
        super(billMaster);
        WebMvcLinkBuilder linkBuilder = linkTo(BillMasterController.class).slash(billMaster.getBillNo());
        add(linkBuilder.withSelfRel());
        add(linkBuilder.withRel("update-billMaster"));
        add(linkTo(BillMasterController.class).withRel("query-billMaster"));
    }

}
