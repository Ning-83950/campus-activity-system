package com.project.demo.controller;

import com.project.demo.entity.FeedbackEvaluation;
import com.project.demo.service.FeedbackEvaluationService;
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
 * 反馈评价：(FeedbackEvaluation)表控制层
 *
 */
@RestController
@RequestMapping("/feedback_evaluation")
public class FeedbackEvaluationController extends BaseController<FeedbackEvaluation, FeedbackEvaluationService> {

    /**
     * 反馈评价对象
     */
    @Autowired
    public FeedbackEvaluationController(FeedbackEvaluationService service) {
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
