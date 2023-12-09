package com.data.stock.openfeign.eastmoney.feign;

import com.data.stock.openfeign.eastmoney.domain.EastDTPoolDTO;
import com.data.stock.openfeign.eastmoney.domain.EastMoneyResult;
import com.data.stock.openfeign.eastmoney.domain.EastZBPoolDTO;
import com.data.stock.openfeign.eastmoney.domain.EastZTPoolDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eastMoneyPoolFeign", url = "https://push2ex.eastmoney.com")
public interface EastMoneyPoolFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/getTopicZTPool")
    EastMoneyResult<EastZTPoolDTO> getTopicZTPool(@RequestParam("Pageindex") Integer Pageindex, @RequestParam("pagesize") Integer pagesize, @RequestParam("ut") String ut,
                                                  @RequestParam("dpt") String dpt, @RequestParam("sort") String sort, @RequestParam("date") String date);

    @RequestMapping(method = RequestMethod.GET, value = "/getTopicDTPool")
    EastMoneyResult<EastDTPoolDTO> getTopicDTPool(@RequestParam("Pageindex") Integer Pageindex, @RequestParam("pagesize") Integer pagesize, @RequestParam("ut") String ut,
                                                  @RequestParam("dpt") String dpt, @RequestParam("sort") String sort, @RequestParam("date") String date);

    @RequestMapping(method = RequestMethod.GET, value = "/getTopicZBPool")
    EastMoneyResult<EastZBPoolDTO> getTopicZBPool(@RequestParam("Pageindex") Integer Pageindex, @RequestParam("pagesize") Integer pagesize, @RequestParam("ut") String ut,
                                                  @RequestParam("dpt") String dpt, @RequestParam("sort") String sort, @RequestParam("date") String date);
}
