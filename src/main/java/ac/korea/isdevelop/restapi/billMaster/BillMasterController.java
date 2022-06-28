package ac.korea.isdevelop.restapi.billMaster;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/billMasters", produces = MediaTypes.HAL_JSON_VALUE)
public class BillMasterController {

    @PostMapping
    public ResponseEntity createBillMaster(@RequestBody BillMaster billMaster){
        URI uri = linkTo(BillMasterController.class).slash("{billNo}").toUri();
        billMaster.setBillNo(10);
        return ResponseEntity.created(uri).body(billMaster);

    }
}
