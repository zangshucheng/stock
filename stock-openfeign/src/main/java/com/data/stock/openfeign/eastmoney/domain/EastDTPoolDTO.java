package com.data.stock.openfeign.eastmoney.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class EastDTPoolDTO implements Serializable {
    /**
     * tc
     */
    private Integer tc;
    /**
     * qdate
     */
    private Integer qdate;
    /**
     * pool
     */
    private List<DTPoolDTO> pool;

    /**
     * PoolDTO
     */
    @NoArgsConstructor
    @Data
    public static class DTPoolDTO implements Serializable{
        /**
         * c
         */
        private String c;
        /**
         * m
         */
        private Integer m;
        /**
         * n
         */
        private String n;
        /**
         * p
         */
        private Integer p;
        /**
         * zdp
         */
        private Double zdp;
        /**
         * amount
         */
        private Long amount;
        /**
         * ltsz
         */
        private Long ltsz;
        /**
         * tshare
         */
        private Long tshare;
        /**
         * pe
         */
        private Double pe;
        /**
         * hs
         */
        private Double hs;
        /**
         * fund
         */
        private Integer fund;
        /**
         * lbt
         */
        private Long lbt;
        /**
         * fba
         */
        private Long fba;
        /**
         * days
         */
        private Integer days;
        /**
         * oc
         */
        private Integer oc;
        /**
         * hybk
         */
        private String hybk;
    }
}
