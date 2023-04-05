package com.xwh.api;

import com.xwh.service.LinkEntityService;
import com.xwh.whblogcommon.utils.R;
import com.xwh.whblogcommon.vo.LinkVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 陈方银
 * @date 2023/4/5
 * @since 1.0
 */


@Tag(name = "link",description = "友链模块")
@RequestMapping("/blog/link")
@RestController
public class LinkEntityController {

    @Autowired
    private LinkEntityService linkEntityService;


    @Operation(summary = "查询所有友链",description = "返回友链对象",
        responses = {@ApiResponse(responseCode = "2xx",description = "返回友链对象")}
    )
    @GetMapping("/getAllLink")
    public R getAllLink() {
        List<LinkVo> linkVos = linkEntityService.getAllLink();
        return R.ok().put(linkVos);
    }
}
