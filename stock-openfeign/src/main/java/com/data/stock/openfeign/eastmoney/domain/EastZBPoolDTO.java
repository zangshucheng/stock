package com.data.stock.openfeign.eastmoney.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class EastZBPoolDTO implements Serializable {
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
    private List<ZBPoolDTO> pool;

    /**
     * PoolDTO
     */
    @NoArgsConstructor
    @Data
    public static class ZBPoolDTO {
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
         * ztp
         */
        private Integer ztp;
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
         * fbt
         */
        private Integer fbt;
        /**
         * zbc
         */
        private Integer zbc;
        /**
         * zf
         */
        private Double zf;
        /**
         * zs
         */
        private Double zs;
        /**
         * zttj
         */
        private ZttjDTO zttj;
        /**
         * hybk
         */
        private String hybk;

        /**
         * ZttjDTO
         */
        @NoArgsConstructor
        @Data
        public static class ZttjDTO {
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
