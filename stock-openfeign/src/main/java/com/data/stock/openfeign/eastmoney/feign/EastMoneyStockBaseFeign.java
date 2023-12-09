package com.data.stock.openfeign.eastmoney.feign;


import com.data.stock.openfeign.eastmoney.domain.EastLatestStockOriginDTO;
import com.data.stock.openfeign.eastmoney.domain.EastMoneyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eastMoneyStockBaseFeign", url = "https://83.push2.eastmoney.com")
public interface EastMoneyStockBaseFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/api/qt/clist/get")
    EastMoneyResult<EastLatestStockOriginDTO> getLatestStockInfo(@RequestParam("pn") Integer pn, @RequestParam("pz")Integer pz, @RequestParam("po")Integer po,
                                                                 @RequestParam("np")Integer np, @RequestParam("fltt")Integer fltt, @RequestParam("invt")Integer invt,
                                                                 @RequestParam("fid")String fid, @RequestParam("fs")String fs, @RequestParam("fields")String fields);
}
