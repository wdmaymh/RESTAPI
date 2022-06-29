package ac.korea.isdevelop.restapi.billMaster;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/billMasters", produces = MediaTypes.HAL_JSON_VALUE)
public class BillMasterController {

    private final ModelMapper modelMapper;
    private final BillMasterRepository billMasterRepository;
    private final BillMasterValidator billMasterValidator;

    public BillMasterController(ModelMapper modelMapper, BillMasterRepository billMasterRepository, BillMasterValidator billMasterValidator) {
        this.modelMapper = modelMapper;
        this.billMasterRepository = billMasterRepository;
        this.billMasterValidator = billMasterValidator;
    }

    @PostMapping
    public ResponseEntity<?> createBillMaster(@RequestBody @Valid BillMasterDto billMasterDto, Errors errors) {

        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }
        billMasterValidator.validate(billMasterDto, errors);
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }

        BillMaster billMaster = modelMapper.map(billMasterDto, BillMaster.class);
        BillMaster newBillMaster = billMasterRepository.save(billMaster);
        WebMvcLinkBuilder linkBuilder = linkTo(BillMasterController.class).slash(newBillMaster.getBillNo());
        URI uri = linkBuilder.toUri();
        BillMasterResource billMasterResource = new BillMasterResource(newBillMaster);
        return ResponseEntity.created(uri).body(billMasterResource);

    }
}
