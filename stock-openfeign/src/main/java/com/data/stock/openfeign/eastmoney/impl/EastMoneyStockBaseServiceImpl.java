package com.data.stock.openfeign.eastmoney.impl;

import com.data.stock.common.constant.MagicNumberConstants;
import com.data.stock.common.utils.DateUtil;
import com.data.stock.common.utils.NullUtil;
import com.data.stock.openfeign.eastmoney.EastMoneyStockBaseService;
import com.data.stock.openfeign.eastmoney.domain.EastLatestStockDTO;
import com.data.stock.openfeign.eastmoney.domain.EastLatestStockOriginDTO;
import com.data.stock.openfeign.eastmoney.domain.EastMoneyResult;
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

    @Override
    public List<EastLatestStockDTO> getLatestAllStockInfo() {

        EastMoneyResult<EastLatestStockOriginDTO> result = null;
        List<EastLatestStockDTO> latestStockOriginDTOList = new ArrayList<>();

        int start = 1;
        //深证A股
        while (true){
            result = eastMoneyStockBaseFeign.getLatestStockInfo(start, MagicNumberConstants.STOCK_BASIC_LIMIT, 1, 1, 2, 2,
                    "f3", "m:0+t:6,m:0+t:80,m:1+t:2,m:1+t:23,m:0+t:81+s:2048", "f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f86,f128,f136,f115,f152");

            if (NullUtil.isNull(result) || NullUtil.isNull(result.getData()) ||CollectionUtils.isEmpty(result.getData().getDiff())) {
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
}
