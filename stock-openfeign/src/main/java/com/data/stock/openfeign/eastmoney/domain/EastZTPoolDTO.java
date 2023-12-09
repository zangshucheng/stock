package com.data.stock.openfeign.eastmoney.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class EastZTPoolDTO implements Serializable {

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
    private List<ZTPoolDTO> pool;

    /**
     * PoolDTO
     */
    @NoArgsConstructor
    @Data
    public static class ZTPoolDTO implements Serializable{
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
         * hs
         */
        private Double hs;
        /**
         * lbc
         */
        private Integer lbc;
        /**
         * fbt
         */
        private Integer fbt;
        /**
         * lbt
         */
        private Integer lbt;
        /**
         * fund
         */
        private Integer fund;
        /**
         * zbc
         */
        private Integer zbc;
        /**
         * hybk
         */
        private String hybk;
        /**
         * zttj
         */
        private ZttjDTO zttj;

        /**
         * ZttjDTO
         */
        @NoArgsConstructor
        @Data
        public static class ZttjDTO implements Serializable{
            /**
             * days
             */
            private Integer days;
            /**
             * ct
             */
            private Integer ct;
        }
    }
}
