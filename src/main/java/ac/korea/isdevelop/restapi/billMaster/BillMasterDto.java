package ac.korea.isdevelop.restapi.billMaster;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BillMasterDto {

    @NotEmpty
    public String accYear;
    @NotEmpty
    public String campusCd;
    @NotEmpty
    public String accUnitCd;
    public String billDt;
    public String billDeptCd;
    public String billDivCd;
    public String billTypeCd;
    public String billKindCd;
    public String wrtEmpObjNo;
    public String billTitle;
    public String digest;
    public String billStsCd;
    public String interfaceKey;
    public String interfaceDivCd;

    @QueryProjection
    public BillMasterDto(String accYear, String campusCd, String accUnitCd) {
        this.accYear = accYear;
        this.campusCd = campusCd;
        this.accUnitCd = accUnitCd;
    }


}
