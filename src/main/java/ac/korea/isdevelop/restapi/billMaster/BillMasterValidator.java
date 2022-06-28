package ac.korea.isdevelop.restapi.billMaster;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class BillMasterValidator {

    public void validate(BillMasterDto billMasterDto, Errors errors){
        String campusCd = billMasterDto.getCampusCd();
        if(!campusCd.equals("1") && !campusCd.equals("2") ){
            errors.rejectValue("campusCd", "wrongValue", "캠퍼스 코드 값은 1(서울) 2(세종)만 가능");
        }
    }
}

