package ac.korea.isdevelop.restapi.index;

import ac.korea.isdevelop.restapi.billMaster.BillMasterController;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class IndexController {

    @GetMapping("/api")
    public RepresentationModel<?> index() {

        var index = new RepresentationModel<>();
        index.add(linkTo(BillMasterController.class).withRel("billMasters"));
        return index;


    }


}
