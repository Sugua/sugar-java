package pdf.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class DuizhangDomain implements Serializable {
    private static final long serialVersionUID = -5342903599035421549L;
    private String jg;
    private Integer ydz;
    private Integer wdz;
    private BigDecimal dzl;
}