package ac.korea.isdevelop.restapi.billMaster;

import ac.korea.isdevelop.restapi.cardProof.CardProof;
import jdk.jfr.Name;
import lombok.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = " billNo")
@Entity
@Table(name = "NACC200TL")
@SequenceGenerator(name="billNoSeq", sequenceName="SEQ_NACC200_SEQ", allocationSize=1)
public class BillMaster {

    @Transient
    private final String TABLE_PREFIX = "NACC200_";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billNoSeq")
    @Column(name = TABLE_PREFIX + "billNo")
    private long billNo;
    @Column(name = TABLE_PREFIX + "accYear", columnDefinition = "char(4)")
    private String accYear;
    @Column(name = TABLE_PREFIX + "campusCd", columnDefinition = "char(1)")
    private String campusCd;
    @Column(name = TABLE_PREFIX + "accUnitCd", columnDefinition = "char(2)")
    private String accUnitCd;
    @Column(name = TABLE_PREFIX + "billDt", columnDefinition = "varchar2(8)")
    private String billDt;
    @Column(name = TABLE_PREFIX + "billDocNo", columnDefinition = "varchar2(14)")
    private String billDocNo;
    @Column(name = TABLE_PREFIX + "billDeptCd", columnDefinition = "char(4)")
    private String billDeptCd;
    @Column(name = TABLE_PREFIX + "billDivCd", columnDefinition = "char(2)")
    private String billDivCd;
    @Column(name = TABLE_PREFIX + "billTypeCd", columnDefinition = "char(3)")
    private String billTypeCd;
    @Column(name = TABLE_PREFIX + "billKindCd", columnDefinition = "char(3)")
    private String billKindCd;
    @Column(name = TABLE_PREFIX + "wrtEmpObjNo", columnDefinition = "varchar2(6)")
    private String wrtEmpObjNo;
    @Column(name = TABLE_PREFIX + "billTitle", columnDefinition = "varchar2(1000)")
    private String billTitle;
    @Column(name = TABLE_PREFIX + "digest", columnDefinition = "varchar2(4000)")
    private String digest;
    @Column(name = TABLE_PREFIX + "billStsCd", columnDefinition = "char(2)")
    private String billStsCd;
    @Column(name = TABLE_PREFIX + "billDeptAprvDt", columnDefinition = "varchar2(8)")
    private String billDeptAprvDt;
    @Column(name = TABLE_PREFIX + "deptAprvEmpObjNo", columnDefinition = "varchar2(10)")
    private String deptAprvEmpObjNo;
    @Column(name = TABLE_PREFIX + "accDt", columnDefinition = "varchar2(8)")
    private String accDt;
    @Column(name = TABLE_PREFIX + "execAprvDt", columnDefinition = "varchar2(8)")
    private String execAprvDt;
    @Column(name = TABLE_PREFIX + "billGrpNo")
    private Long billGrpNo;
    @Column(name = TABLE_PREFIX + "interfaceKey", columnDefinition = "varchar2(100)")
    private String interfaceKey;
    @Column(name = TABLE_PREFIX + "interfaceDivCd", columnDefinition = "varchar2(7)")
    private String interfaceDivCd;
    private LocalDateTime crtDttm;
    @Column(columnDefinition = "varchar2(20)")
    private String crtId;
    @Column(columnDefinition = "varchar2(50)")
    private String crtPgmId;
    @Column(columnDefinition = "varchar2(20)")
    private String crtIp;
    @OneToMany(mappedBy = "billMaster", fetch = FetchType.LAZY)
    private Set<CardProof> cardProofset = new LinkedHashSet<>();
}