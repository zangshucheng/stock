package com.data.stock.openfeign.eastmoney.impl;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.common.utils.NullUtil;
import com.data.stock.openfeign.eastmoney.EastMoneyStockBaseService;
import com.data.stock.openfeign.eastmoney.domain.*;
import com.data.stock.openfeign.eastmoney.feign.EastMoneyPoolFeign;
import com.data.stock.openfeign.eastmoney.feign.EastMoneyStockBaseFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EastMoneyStockBaseServiceImpl implements EastMoneyStockBaseService {

    @Autowired
    private EastMoneyStockBaseFeign eastMoneyStockBaseFeign;

    @Autowired
    private EastMoneyPoolFeign eastMoneyPoolFeign;

    @Override
    public List<EastLatestStockDTO> getLatestAllStockInfo() {

        EastMoneyResult<EastLatestStockOriginDTO> result = null;
        List<EastLatestStockDTO> latestStockOriginDTOList = new ArrayList<>();

        int start = 1;
        //深证A股
        while (true) {
            result = eastMoneyStockBaseFeign.getLatestStockInfo(start, MagicNumberConstants.STOCK_BASIC_LIMIT, 1, 1, 2, 2,
                    "f3", "m:0+t:6,m:0+t:80,m:1+t:2,m:1+t:23,m:0+t:81+s:2048", "f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f86,f128,f136,f115,f152");

            if (NullUtil.isNull(result) || NullUtil.isNull(result.getData()) || CollectionUtils.isEmpty(result.getData().getDiff())) {
                break;
            }
            start += 1;
            latestStockOriginDTOList.addAll(result.getData().getDiff());
        }

        return latestStockOriginDTOList;
    }

    @Override
    public EastLatestStockDTO getLatestStockInfo(String stockName) {
        return null;
    }

    @Override
    public List<EastZTPoolDTO.ZTPoolDTO> getTopicZTPool(String tradeDate) {
        EastMoneyResult<EastZTPoolDTO> topicZTPool = null;
        List<EastZTPoolDTO.ZTPoolDTO> ztPoolList = new ArrayList<>();
        int start = 0;
        do {
            topicZTPool = eastMoneyPoolFeign.getTopicZTPool(start, MagicNumberConstants.STOCK_POOL_LIMIT,
                    "7eea3edcaed734bea9cbfc24409ed989", "wz.ztzt", "fbt%3Aasc", tradeDate);

            if(NullUtil.isNotNull(topicZTPool) && NullUtil.isNotNull(topicZTPool.getData())
                    && CollectionUtils.isEmpty(topicZTPool.getData().getPool())){
                ztPoolList.addAll(topicZTPool.getData().getPool());
            }

        } while (NullUtil.isNotNull(topicZTPool) && NullUtil.isNotNull(topicZTPool.getData())
                && CollectionUtils.isEmpty(topicZTPool.getData().getPool())
                && topicZTPool.getData().getPool().size() == MagicNumberConstants.STOCK_POOL_LIMIT);

        return ztPoolList;
    }

    @Override
    public List<EastDTPoolDTO.DTPoolDTO> getTopicDTPool(String tradeDate) {
        EastMoneyResult<EastDTPoolDTO> topicDTPool;
        List<EastDTPoolDTO.DTPoolDTO> dtPoolList = new ArrayList<>();
        int start = 0;
        do {
            topicDTPool = eastMoneyPoolFeign.getTopicDTPool(start, MagicNumberConstants.STOCK_POOL_LIMIT,
                    "7eea3edcaed734bea9cbfc24409ed989", "wz.ztzt", "fund%3Aasc", tradeDate);

            if(NullUtil.isNotNull(topicDTPool) && NullUtil.isNotNull(topicDTPool.getData())
                    && CollectionUtils.isEmpty(topicDTPool.getData().getPool())){
                dtPoolList.addAll(topicDTPool.getData().getPool());
            }

        } while (NullUtil.isNotNull(topicDTPool) && NullUtil.isNotNull(topicDTPool.getData())
                && CollectionUtils.isEmpty(topicDTPool.getData().getPool())
                && topicDTPool.getData().getPool().size() == MagicNumberConstants.STOCK_POOL_LIMIT);

        return dtPoolList;
    }

    @Override
    public List<EastZBPoolDTO.ZBPoolDTO> getTopicZBPool(String tradeDate) {
        EastMoneyResult<EastZBPoolDTO> topicZBPool;
        List<EastZBPoolDTO.ZBPoolDTO> zbPoolList = new ArrayList<>();
        int start = 0;
        do {
            topicZBPool = eastMoneyPoolFeign.getTopicZBPool(start, MagicNumberConstants.STOCK_POOL_LIMIT,
                    "7eea3edcaed734bea9cbfc24409ed989", "wz.ztzt", "fbt%3Aasc", tradeDate);

            if(NullUtil.isNotNull(topicZBPool) && NullUtil.isNotNull(topicZBPool.getData())
                    && CollectionUtils.isEmpty(topicZBPool.getData().getPool())){
                zbPoolList.addAll(topicZBPool.getData().getPool());
            }

        } while (NullUtil.isNotNull(topicZBPool) && NullUtil.isNotNull(topicZBPool.getData())
                && CollectionUtils.isEmpty(topicZBPool.getData().getPool())
                && topicZBPool.getData().getPool().size() == MagicNumberConstants.STOCK_POOL_LIMIT);

        return zbPoolList;
    }
}
