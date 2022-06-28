package ac.korea.isdevelop.restapi.billMaster;

import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;
    private final BillMasterRepository billMasterRepository;

    public BillMasterController(ModelMapper modelMapper, BillMasterRepository billMasterRepository) {
        this.modelMapper = modelMapper;
        this.billMasterRepository = billMasterRepository;
    }

    @PostMapping
    public ResponseEntity createBillMaster(@RequestBody BillMasterDto billMasterDto) {

        BillMaster billMaster = modelMapper.map(billMasterDto, BillMaster.class);
        BillMaster newBillMaster = billMasterRepository.save(billMaster);
        URI uri = linkTo(BillMasterController.class).slash(newBillMaster.getBillNo()).toUri();
        return ResponseEntity.created(uri).body(billMaster);

    }
}
