package ac.korea.isdevelop.restapi.billMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/billMasters", produces = MediaTypes.HAL_JSON_VALUE)
public class BillMasterController {

    private final BillMasterRepository billMasterRepository;

    public BillMasterController(BillMasterRepository billMasterRepository) {
        this.billMasterRepository = billMasterRepository;
    }

    @PostMapping
    public ResponseEntity createBillMaster(@RequestBody BillMaster billMaster){
        BillMaster newBillMaster = billMasterRepository.save(billMaster);
        URI uri = linkTo(BillMasterController.class).slash(newBillMaster.getBillNo()).toUri();
        return ResponseEntity.created(uri).body(billMaster);

    }
}
