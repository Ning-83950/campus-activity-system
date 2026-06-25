package com.project.demo.controller;

import com.project.demo.entity.ActivityCosts;
import com.project.demo.service.ActivityCostsService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 活动费用：(ActivityCosts)表控制层
 *
 */
@RestController
@RequestMapping("/activity_costs")
public class ActivityCostsController extends BaseController<ActivityCosts, ActivityCostsService> {

    /**
     * 活动费用对象
     */
    @Autowired
    public ActivityCostsController(ActivityCostsService service) {
        setService(service);
    }



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }


}
