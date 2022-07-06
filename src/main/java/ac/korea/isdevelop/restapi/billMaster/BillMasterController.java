package ac.korea.isdevelop.restapi.billMaster;

import ac.korea.isdevelop.restapi.common.ErrorsResource;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

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

        if (errors.hasErrors()) {
            return badRequest(errors);
        }
        billMasterValidator.validate(billMasterDto, errors);
        if (errors.hasErrors()) {
            return badRequest(errors);
        }

        BillMaster billMaster = modelMapper.map(billMasterDto, BillMaster.class);
        BillMaster newBillMaster = billMasterRepository.save(billMaster);
        WebMvcLinkBuilder linkBuilder = linkTo(BillMasterController.class).slash(newBillMaster.getBillNo());
        URI uri = linkBuilder.toUri();
        BillMasterResource billMasterResource = new BillMasterResource(newBillMaster);
        billMasterResource.add(Link.of("/docs/index.html#resources-billMasters-create").withRel("profile"));
        return ResponseEntity.created(uri).body(billMasterResource);

    }

    private ResponseEntity<?> badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors));
    }

    @GetMapping("/{accyear}/{campusCd}/{accUnitCd}")
    public ResponseEntity<?> queryBillMasters(@PathVariable String accyear, @PathVariable String campusCd, @PathVariable String accUnitCd,
                                              Pageable pageable, PagedResourcesAssembler<BillMaster> assembler) {
        //Page<BillMaster> page = this.billMasterRepository.findAll(pageable);
        Page<BillMaster> page = this.billMasterRepository.findByAccYearEqualsAndCampusCdEqualsAndAccUnitCdEquals
                (accyear, campusCd, accUnitCd, pageable);
        var pagedModel = assembler.toModel(page, b -> new BillMasterResource(b));
        pagedModel.add(Link.of("/docs/index.html#resources-billMasters-list").withRel("profile"));
        return ResponseEntity.ok(pagedModel);


    }

    @GetMapping("/{id}")
    public ResponseEntity<?> queryBillMaster(@PathVariable Long id) {
        Optional<BillMaster> optionalBillMaster = this.billMasterRepository.findById(id);

        if (optionalBillMaster.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BillMaster billMaster = optionalBillMaster.get();
        BillMasterResource billMasterResource = new BillMasterResource(billMaster);
        billMasterResource.add(Link.of("/docs/index.html#resources-billMasters-get").withRel("profile"));
        return ResponseEntity.ok(billMasterResource);

    }
}
