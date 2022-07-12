package ac.korea.isdevelop.restapi.cardProof;


import ac.korea.isdevelop.restapi.billMaster.BillMaster;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of="proofNo")
@Entity
@Table(name = "NACC300TL")
@SequenceGenerator(name="cardProofSeq", sequenceName="SEQ_NACC300_CORP_CARD_PROOF_NO", allocationSize=1)
public class CardProof {

    @Transient
    private final String TABLE_PREFIX = "NACC300_";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardProofSeq")
    @Column(name = TABLE_PREFIX + "corpCardProofNo")
    private Long proofNo;
    @Column(name = TABLE_PREFIX + "billSeq")
    private int billSeq;
    @Column(name = TABLE_PREFIX + "cardNo")
    private String cardNo;
    @Column(name = TABLE_PREFIX + "cardAprvNo")
    private String cardAprvNo;
    @Column(name = TABLE_PREFIX + "cardAprvDt")
    private String cardAprvDt;
    @Column(name = TABLE_PREFIX + "trdSeq")
    private String trdSeq;
    @Column(name = TABLE_PREFIX + "trdDt")
    private String trdDt;
    @Column(name = TABLE_PREFIX + "cardSettDt")
    private String cardSettDt;
    @Column(name = TABLE_PREFIX + "bussNo")
    private String bussNo;
    @Column(name = TABLE_PREFIX + "vendNm")
    private String vendNm;
    @Column(name = TABLE_PREFIX + "cardUseNm")
    private String cardUseNm;
    @Column(name = TABLE_PREFIX + "aprvAmt")
    private Long aprvAmt;
    @Column(name = TABLE_PREFIX + "useAmt")
    private Long useAmt;
    @Column(name = TABLE_PREFIX + "suplyAmt")
    private Long suplyAmt;
    @Column(name = TABLE_PREFIX + "taxAmt")
    private Long taxAmt;
    @Column(name = TABLE_PREFIX + "chargeAmt")
    private Long chargeAmt;
    private LocalDateTime crt_dttm;
    private String crtId;
    private String crtPgmId;
    private String crtIp;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NACC300_BILL_NO")
    private BillMaster billMaster;


}


