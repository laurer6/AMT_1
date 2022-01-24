package ch.heig.amt.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    private String userLogin;
    private BigDecimal solde;
    private String code;
}
